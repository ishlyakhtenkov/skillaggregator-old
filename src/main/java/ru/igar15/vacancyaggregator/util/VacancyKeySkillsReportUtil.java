package ru.igar15.vacancyaggregator.util;

import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.to.VacancyKeySkillsReportTo;

import java.util.LinkedHashMap;
import java.util.Map;

public class VacancyKeySkillsReportUtil {

    private VacancyKeySkillsReportUtil() {
        throw new UnsupportedOperationException();
    }

    public static VacancyKeySkillsReportTo getTo(VacancyKeySkillsReport report) {
        String keySkills = report.getKeySkills();
        Map<String, String> keySkillsMap = new LinkedHashMap<>();
        String[] split = keySkills.split("\n");
        for (String temp : split) {
            String[] tempSplit = temp.split("=%=");
            keySkillsMap.put(tempSplit[0], tempSplit[1]);
        }
        return new VacancyKeySkillsReportTo(report.getId(), report.getName(), report.getCity(), report.getDate(), report.getSelection(), report.getVacanciesAmount(), keySkillsMap);
    }

    public static String getS() {
        return "Hui";
    }
}
