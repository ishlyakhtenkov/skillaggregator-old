package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.util.SkillsReportUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static ru.igar15.vacancyaggregator.aggregator.SkillsConstants.*;

public abstract class AbstractSkillsAggregator implements Aggregator<SkillsReport> {
    private static final String HH_RU_URL_SAMPLE = "https://hh.ru/search/vacancy?text=%s+%s&search_field=name&page=%s";

    @Autowired
    protected DocumentCreator documentCreator;

    @Autowired
    protected SkillsParser htmlParser;

    @Override
    public Optional<SkillsReport> getReport(String name, String city, int selection) throws IOException {
        SkillsStatistic skillsStatistic = aggregateSkillsStatistic(name, city, selection);
        if (skillsStatistic.vacanciesAmount[0] == 0) {
            return Optional.empty();
        } else {
            return Optional.of(SkillsReportUtil.get(name, city, selection, skillsStatistic.vacanciesAmount[0], skillsStatistic.skillsMap));
        }
    }

    private SkillsStatistic aggregateSkillsStatistic(String name, String city, int selection) throws IOException {
        String hhRuUrl = String.format(HH_RU_URL_SAMPLE, name, city, "%s");
        int[] vacanciesAmount = {0};
        Map<String, Integer> skillsMap = new HashMap<>();

        Properties vacancyProperties = new Properties();
        boolean isVacancyPropertiesExist = checkVacancyPropertiesExist(name, vacancyProperties);

        getAllVacanciesSkills(hhRuUrl, selection, isVacancyPropertiesExist, vacancyProperties, skillsMap, vacanciesAmount);

        return new SkillsStatistic(vacanciesAmount, skillsMap);
    }

    public abstract void getAllVacanciesSkills(String hhRuUrl, int selection, boolean isVacancyPropertiesExist,
                                               Properties vacancyProperties, Map<String, Integer> skillsMap, int[] vacanciesAmount) throws IOException;

    protected void getVacancySkills(String vacancyUrl, boolean isVacancyPropertiesExist,
                                    Properties vacancyProperties, Map<String, Integer> skillsMap, int[] vacanciesAmount) throws IOException {
        vacanciesAmount[0] = vacanciesAmount[0] + 1;
        Document vacancyPage = documentCreator.getDocument(vacancyUrl);
        Elements vacancySkills = htmlParser.getVacancySkills(vacancyPage);
        for (Element vacancySkill : vacancySkills) {
            String skill = vacancySkill.text().toUpperCase();
            if (skill.contains(ENGLISH_KEY_SKILL_PATTERN)) {
                skill = ENGLISH_KEY_SKILL_VALUE;
            }
            if (isVacancyPropertiesExist && vacancyProperties.containsKey(skill)) {
                skill = vacancyProperties.getProperty(skill);
            }
            skillsMap.merge(skill, 1, Integer::sum);
        }
    }

    private boolean checkVacancyPropertiesExist(String name, Properties vacancyProperties) {
        if (name.contains(JAVA) && !name.contains(JAVASCRIPT) && !name.contains(JAVA_SCRIPT)) {
            try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("vacancies/javaVacancy.properties")) {
                vacancyProperties.load(is);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    private static class SkillsStatistic {
        private final int[] vacanciesAmount;
        private final Map<String, Integer> skillsMap;

        public SkillsStatistic(int[] vacanciesAmount, Map<String, Integer> skillsMap) {
            this.vacanciesAmount = vacanciesAmount;
            this.skillsMap = skillsMap;
        }
    }
}