package ru.igar15.skillsaggregator.util;

import ru.igar15.skillsaggregator.model.Selection;
import ru.igar15.skillsaggregator.model.SkillsReport;
import ru.igar15.skillsaggregator.to.SkillsReportTo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SkillsReportUtil {
    private final static String DELIMITER = "=%=";

    private SkillsReportUtil() {
    }

    public static SkillsReportTo getTo(SkillsReport report) {
        String skills = report.getRequiredSkills();
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
        return new SkillsReportTo(report.getId(), report.getProfessionName(), report.getCity(), report.getDate(), report.getSelection(), report.getAnalyzedVacanciesAmount(), skillsMap);
    }

    public static SkillsReport get(String professionName, String city, int analyzedVacanciesAmount, Map<String, Integer> skillsMap, Selection selection) {
        StringBuilder requiredSkillsBuilder = new StringBuilder();
        if (analyzedVacanciesAmount > 0 && skillsMap.size() > 0) {
            skillsMap = MapSortUtil.sortMapByValue(skillsMap, Comparator.reverseOrder());
            skillsMap.forEach((k, v) -> {
                long percent = Math.round((double) v / analyzedVacanciesAmount * 100);
                if (percent >= 100) {
                    percent = 100;
                }
                if (percent > 0) {
                    requiredSkillsBuilder.append(k)
                            .append(DELIMITER)
                            .append(percent)
                            .append(" %")
                            .append("\n");
                }
            });
        }
        String requiredSkills = requiredSkillsBuilder.toString().trim();
        return new SkillsReport(professionName, city, analyzedVacanciesAmount, requiredSkills, selection);
    }
}