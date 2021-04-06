package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.util.Util;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class VacancyKeySkillsAggregator implements VacancyAggregator {
    private static final String HH_RU_URL_SAMPLE = "https://hh.ru/search/vacancy?text=%s+%s&page=%s";

    @Autowired
    private HtmlDocumentCreator htmlDocumentCreator;

    @Autowired
    private VacancyKeySkillsHtmlParser htmlParser;

    public VacancyKeySkillsAggregator() {
    }

    @Override
    public VacancyKeySkillsReport getAggregationResult(String name, String city, int selection) throws IOException {
        KeySkillsStatistic keySkillsStatistic = aggregateKeySkillsStatistic(name, city, selection);
        return new VacancyKeySkillsReport(name, city, selection, keySkillsStatistic.vacanciesAmount, getKeySkillsStatisticReport(keySkillsStatistic));
    }

    private KeySkillsStatistic aggregateKeySkillsStatistic(String name, String city, int selection) throws IOException {
        String hhRuUrl = String.format(HH_RU_URL_SAMPLE, name, city, "%s");
        int vacanciesAmount = 0;
        Properties vacancyProperties = new Properties();
        boolean isVacancyPropertiesExist = checkVacancyProperties(name, vacancyProperties);
        Map<String, Integer> keySkills = new HashMap<>();
        int pagesAmount = getPagesAmount(hhRuUrl, selection);
        Elements vacancies = null;
        for (int i = 0; i < pagesAmount; i++) {
            vacancies = getVacancies(hhRuUrl, i);
            for (Element vacancy : vacancies) {
                vacanciesAmount++;
                String vacancyUrl = htmlParser.getVacancyUrl(vacancy);
                Document vacancyPage = htmlDocumentCreator.getDocument(vacancyUrl);
                Elements vacancyKeySkills = htmlParser.getVacancyKeySkills(vacancyPage);
                for (Element vacancyKeySkill : vacancyKeySkills) {
                    String keySkill = vacancyKeySkill.text().toUpperCase();
                    if (keySkill.contains("АНГЛИЙСКИЙ")) {
                        keySkill = "АНГЛИЙСКИЙ ЯЗЫК";
                    }
                    if (isVacancyPropertiesExist) {
                        if (vacancyProperties.containsKey(keySkill)) {
                            keySkill = vacancyProperties.getProperty(keySkill);
                        }
                    }
                    keySkills.merge(keySkill, 1, Integer::sum);
                }
            }
        }
        return new KeySkillsStatistic(name, city, vacanciesAmount, keySkills);
    }

    private int getPagesAmount(String url, int selection) throws IOException {
        int pagesAmount = 0;
        for (int i = 0; i < 3; i++) {
            Document firstPage = htmlDocumentCreator.getDocument(String.format(url, 0));
            pagesAmount = htmlParser.getPagesAmount(firstPage);
            if (pagesAmount > 0) {
                break;
            }
        }
        return Math.min(pagesAmount, selection);
    }

    private Elements getVacancies(String url, int pageNumber) throws IOException {
        Document vacancyPage = null;
        Elements vacancyElements = null;
        for (int i = 0; i < 10; i++) {
            vacancyPage = htmlDocumentCreator.getDocument(String.format(url, i));
            vacancyElements = htmlParser.getVacancyElements(vacancyPage);
            if (vacancyElements.size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return vacancyElements;
    }

    private String getKeySkillsStatisticReport(KeySkillsStatistic keySkillsStatistic) {
        StringBuilder builder = new StringBuilder();
        Util.sortMapByValue(keySkillsStatistic.keySkills, Comparator.reverseOrder()).forEach((k, v) -> {
            long percent = Math.round((double) v / keySkillsStatistic.vacanciesAmount * 100);
            if (percent >= 100) {
                percent = 100;
            }
            if (percent > 0) {
                builder.append(k)
                        .append("=%=")
                        .append(percent)
                        .append(" %")
                        .append("\n");
            }
        });
        return builder.toString().trim();
    }

    private boolean checkVacancyProperties(String name, Properties vacancyProperties) {
        try {
            if (name.contains("JAVA") && !name.contains("JAVASCRIPT") && !name.contains("JAVA SCRIPT")) {
                vacancyProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("vacancies/javaVacancy.properties"));
                return true;
            }
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }

    private static class KeySkillsStatistic {
        private final String name;
        private final String city;
        private final int vacanciesAmount;
        private final Map<String, Integer> keySkills;

        public KeySkillsStatistic(String name, String city, int vacanciesAmount, Map<String, Integer> keySkills) {
            this.name = name;
            this.city = city;
            this.vacanciesAmount = vacanciesAmount;
            this.keySkills = keySkills;
        }
    }
}