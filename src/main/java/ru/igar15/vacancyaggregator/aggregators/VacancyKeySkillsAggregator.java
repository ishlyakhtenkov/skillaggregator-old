package ru.igar15.vacancyaggregator.aggregators;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.util.Util;

import java.io.IOException;
import java.util.*;

@Component
public class VacancyKeySkillsAggregator implements VacancyAggregator {
    private static final String HH_RU_URL_SAMPLE = "https://hh.ru/search/vacancy?text=%s+%s&page=%s";

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

        int pageAmount = getPageAmount(hhRuUrl, selection);
        Document vacanciesListPage = null;
        Elements vacancyElements = null;
        for (int i = 0; i < pageAmount; i++) {
            for (int j = 0; j < 10; j++) {
                vacanciesListPage = getDocument(hhRuUrl, i);
                vacancyElements = vacanciesListPage.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
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
            for (Element vacancyElement : vacancyElements) {
                vacanciesAmount++;
                String vacancyUrl = vacancyElement.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                Document vacancyPage = getDocument(vacancyUrl);
                Elements keySkillsElements = vacancyPage.getElementsByAttributeValue("data-qa", "bloko-tag__text");
                for (Element keySkillElement : keySkillsElements) {
                    String keySkill = keySkillElement.text().toUpperCase();
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

    private Document getDocument(String url, int page) throws IOException {
        return Jsoup.connect(String.format(url, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
    }

    private int getPageAmount(String url, int selection) throws IOException {
        int pageAmount = 0;
        for (int i = 0; i < 3; i++) {
            Document document = Jsoup.connect(String.format(url, 0))
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
                    .referrer("http://www.google.com")
                    .get();
            Elements elements = document.getElementsByClass("bloko-button HH-Pager-Control");
            OptionalInt max = elements.stream()
                    .mapToInt(element -> Integer.parseInt(element.text()))
                    .max();
             pageAmount = max.orElse(0);
            if (pageAmount > 0) {
                break;
            }
        }
        return Math.min(pageAmount, selection);
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

    private static class KeySkillsStatistic {
        private String name;
        private String city;
        private int vacanciesAmount;
        private Map<String, Integer> keySkills;

        public KeySkillsStatistic(String name, String city, int vacanciesAmount, Map<String, Integer> keySkills) {
            this.name = name;
            this.city = city;
            this.vacanciesAmount = vacanciesAmount;
            this.keySkills = keySkills;
        }
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
}