package ru.igar15.vacancyaggregator;

import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;

public class VacancyKeySkillsReportTestData {
    private static final String KEY_SKILLS = "JAVA=%=100 %\nSPRING FRAMEWORK=%=49 %\nGIT=%=40 %\nPOSTGRESQL=%=31 %";

    public static final VacancyKeySkillsReport report = new VacancyKeySkillsReport("NAME", "CITY",
            2, 100, KEY_SKILLS);

    public static final VacancyKeySkillsReport reportWithoutVacancies = new VacancyKeySkillsReport("NAME", "CITY",
            2, 0, "");

    public static VacancyKeySkillsReport getNew() {
        return new VacancyKeySkillsReport("NAME", "CITY",2, 100, KEY_SKILLS);
    }
}