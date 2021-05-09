package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.util.SkillsReportUtil;

import java.io.IOException;
import java.util.*;

import static ru.igar15.vacancyaggregator.aggregator.VacancyConstants.*;

@Component
public class SkillsAggregatorV2 implements Aggregator<SkillsReport> {
    private static final String HH_RU_URL_SAMPLE = "https://hh.ru/search/vacancy?text=%s+%s&search_field=name&page=%s";

    @Autowired
    private HtmlDocumentCreator htmlDocumentCreator;

    @Autowired
    private SkillsHtmlParser htmlParser;

    @Override
    public Optional<SkillsReport> getReport(String name, String city, int selection) throws IOException {
        KeySkillsStatistic keySkillsStatistic = aggregateKeySkillsStatistic(name, city, selection);
        if (keySkillsStatistic.vacanciesAmount == 0) {
            return Optional.empty();
        } else {
            return Optional.of(SkillsReportUtil.get(name, city, selection, keySkillsStatistic.vacanciesAmount, keySkillsStatistic.keySkills));
        }
    }

    private KeySkillsStatistic aggregateKeySkillsStatistic(String name, String city, int selection) throws IOException {
        String hhRuUrl = String.format(HH_RU_URL_SAMPLE, name, city, "%s");
        int vacanciesAmount = 0;
        Map<String, Integer> keySkills = new HashMap<>();

        Properties vacancyProperties = new Properties();
        boolean isVacancyPropertiesExist = checkVacancyPropertiesExist(name, vacancyProperties);

        List<String> vacanciesUrl= null;
        for (int i = 0; i < selection; i++) {
            vacanciesUrl = getVacanciesUrl(hhRuUrl, i);
            if (vacanciesUrl.size() == 0) {
                break;
            }
            for (String vacancyUrl : vacanciesUrl) {
                vacanciesAmount++;
                Document vacancyPage = htmlDocumentCreator.getDocument(vacancyUrl);
                Elements vacancyKeySkills = htmlParser.getVacancyKeySkills(vacancyPage);
                for (Element vacancyKeySkill : vacancyKeySkills) {
                    String keySkill = vacancyKeySkill.text().toUpperCase();
                    if (keySkill.contains(ENGLISH_KEY_SKILL_PATTERN)) {
                        keySkill = ENGLISH_KEY_SKILL_VALUE;
                    }
                    if (isVacancyPropertiesExist && vacancyProperties.containsKey(keySkill)) {
                        keySkill = vacancyProperties.getProperty(keySkill);
                    }
                    keySkills.merge(keySkill, 1, Integer::sum);
                }
            }
        }
        return new KeySkillsStatistic(vacanciesAmount, keySkills);
    }

    private List<String> getVacanciesUrl(String url, int pageNumber) throws IOException {
        Document vacanciesPage = null;
        List<String> vacanciesUrl = null;
        for (int i = 0; i < 10; i++) {
            vacanciesPage = htmlDocumentCreator.getDocument(String.format(url, pageNumber));
            vacanciesUrl = htmlParser.getVacanciesUrl(vacanciesPage);
            if (vacanciesUrl.size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return vacanciesUrl;
    }

    private boolean checkVacancyPropertiesExist(String name, Properties vacancyProperties) {
        try {
            if (name.contains(JAVA) && !name.contains(JAVASCRIPT) && !name.contains(JAVA_SCRIPT)) {
                vacancyProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("vacancies/javaVacancy.properties"));
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private static class KeySkillsStatistic {
        private final int vacanciesAmount;
        private final Map<String, Integer> keySkills;

        public KeySkillsStatistic(int vacanciesAmount, Map<String, Integer> keySkills) {
            this.vacanciesAmount = vacanciesAmount;
            this.keySkills = keySkills;
        }
    }
}