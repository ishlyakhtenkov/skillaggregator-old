package ru.igar15.vacancyaggregator.util;

import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.to.SkillsReportTo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SkillsReportUtil {
    private final static String DELIMITER = "=%=";

    private SkillsReportUtil() {
        throw new UnsupportedOperationException();
    }

    public static SkillsReportTo getTo(SkillsReport report) {
        String skills = report.getSkills();
        Map<String, String> skillsMap = new LinkedHashMap<>();
        if (!skills.isBlank()) {
            String[] splitSkills = skills.split("\n");
            for (String skill : splitSkills) {
                if (skill.contains(DELIMITER)) {
                    String[] splitSkill = skill.split(DELIMITER);
                    skillsMap.put(splitSkill[0], splitSkill[1]);
                }
            }
        }
        return new SkillsReportTo(report.getId(), report.getName(), report.getCity(), report.getDate(), report.getSelection(), report.getVacanciesAmount(), skillsMap);
    }

    public static SkillsReport get(String name, String city, int selection, int vacanciesAmount, Map<String, Integer> skillsMap) {
        StringBuilder skillsBuilder = new StringBuilder();
        if (vacanciesAmount > 0 && skillsMap.size() > 0) {
            MapSortUtil.sortMapByValue(skillsMap, Comparator.reverseOrder()).forEach((k, v) -> {
                long percent = Math.round((double) v / vacanciesAmount * 100);
                if (percent >= 100) {
                    percent = 100;
                }
                if (percent > 0) {
                    skillsBuilder.append(k)
                            .append(DELIMITER)
                            .append(percent)
                            .append(" %")
                            .append("\n");
                }
            });
        }
        String skills = skillsBuilder.toString().trim();
        return new SkillsReport(name, city, selection, vacanciesAmount, skills);
    }
}