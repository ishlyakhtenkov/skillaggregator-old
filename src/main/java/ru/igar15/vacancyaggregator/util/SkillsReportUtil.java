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
        String keySkills = report.getKeySkills();
        Map<String, String> keySkillsMap = new LinkedHashMap<>();
        if (!keySkills.isBlank()) {
            String[] split = keySkills.split("\n");
            for (String temp : split) {
                if (temp.contains(DELIMITER)) {
                    String[] tempSplit = temp.split(DELIMITER);
                    keySkillsMap.put(tempSplit[0], tempSplit[1]);
                }
            }
        }
        return new SkillsReportTo(report.getId(), report.getName(), report.getCity(), report.getDate(), report.getSelection(), report.getVacanciesAmount(), keySkillsMap);
    }

    public static SkillsReport get(String name, String city, int selection, int vacanciesAmount, Map<String, Integer> keySkills) {
        StringBuilder builder = new StringBuilder();
        if (vacanciesAmount > 0 && keySkills.size() > 0) {
            Util.sortMapByValue(keySkills, Comparator.reverseOrder()).forEach((k, v) -> {
                long percent = Math.round((double) v / vacanciesAmount * 100);
                if (percent >= 100) {
                    percent = 100;
                }
                if (percent > 0) {
                    builder.append(k)
                            .append(DELIMITER)
                            .append(percent)
                            .append(" %")
                            .append("\n");
                }
            });
        }
        String formattedKeySkills = builder.toString().trim();
        return new SkillsReport(name, city, selection, vacanciesAmount, formattedKeySkills);
    }
}
