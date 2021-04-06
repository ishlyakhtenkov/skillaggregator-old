package ru.igar15.vacancyaggregator.util;

import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.to.VacancyKeySkillsReportTo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class VacancyKeySkillsReportUtil {
    private final static String DELIMITER = "=%=";

    private VacancyKeySkillsReportUtil() {
        throw new UnsupportedOperationException();
    }

    public static VacancyKeySkillsReportTo getTo(VacancyKeySkillsReport report) {
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
        return new VacancyKeySkillsReportTo(report.getId(), report.getName(), report.getCity(), report.getDate(), report.getSelection(), report.getVacanciesAmount(), keySkillsMap);
    }

    public static VacancyKeySkillsReport get(String name, String city, int selection, int vacanciesAmount, Map<String, Integer> keySkills) {
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
        return new VacancyKeySkillsReport(name, city, selection, vacanciesAmount, formattedKeySkills);
    }
}
