package ru.igar15.vacancyaggregator;

import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;

public class VacancyKeySkillsReportTestData {

    public static final VacancyKeySkillsReport report = new VacancyKeySkillsReport("NAME", "CITY",
            2, 100, "keySkills");

    public static final VacancyKeySkillsReport reportWithoutVacancies = new VacancyKeySkillsReport("NAME", "CITY",
            2, 0, "");

    public static VacancyKeySkillsReport getNew() {
        return new VacancyKeySkillsReport("NAME", "CITY",2, 100, "keySkills");
    }
}