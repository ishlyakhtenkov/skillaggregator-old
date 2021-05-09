package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.util.SkillsReportUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static ru.igar15.vacancyaggregator.aggregator.VacancyConstants.*;

public abstract class AbstractSkillsAggregator implements Aggregator<SkillsReport> {
    private static final String HH_RU_URL_SAMPLE = "https://hh.ru/search/vacancy?text=%s+%s&search_field=name&page=%s";

    @Autowired
    protected HtmlDocumentCreator htmlDocumentCreator;

    @Autowired
    protected SkillsHtmlParser htmlParser;

    @Override
    public Optional<SkillsReport> getReport(String name, String city, int selection) throws IOException {
        KeySkillsStatistic keySkillsStatistic = aggregateKeySkillsStatistic(name, city, selection);
        if (keySkillsStatistic.vacanciesAmount[0] == 0) {
            return Optional.empty();
        } else {
            return Optional.of(SkillsReportUtil.get(name, city, selection, keySkillsStatistic.vacanciesAmount[0], keySkillsStatistic.keySkills));
        }
    }

    private KeySkillsStatistic aggregateKeySkillsStatistic(String name, String city, int selection) throws IOException {
        String hhRuUrl = String.format(HH_RU_URL_SAMPLE, name, city, "%s");
        int[] vacanciesAmount = {0};
        Map<String, Integer> keySkills = new HashMap<>();

        Properties vacancyProperties = new Properties();
        boolean isVacancyPropertiesExist = checkVacancyPropertiesExist(name, vacancyProperties);

        getAllVacanciesKeySkills(hhRuUrl, selection, isVacancyPropertiesExist, vacancyProperties, keySkills, vacanciesAmount);

        return new KeySkillsStatistic(vacanciesAmount, keySkills);
    }

    public abstract void getAllVacanciesKeySkills(String hhRuUrl, int selection, boolean isVacancyPropertiesExist,
                                                  Properties vacancyProperties, Map<String, Integer> keySkills, int[] vacanciesAmount) throws IOException;

    protected void getVacancyKeySkills(String vacancyUrl, boolean isVacancyPropertiesExist,
                                    Properties vacancyProperties, Map<String, Integer> keySkills, int[] vacanciesAmount) throws IOException {
        vacanciesAmount[0] = vacanciesAmount[0] + 1;
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
        private final int[] vacanciesAmount;
        private final Map<String, Integer> keySkills;

        public KeySkillsStatistic(int[] vacanciesAmount, Map<String, Integer> keySkills) {
            this.vacanciesAmount = vacanciesAmount;
            this.keySkills = keySkills;
        }
    }
}