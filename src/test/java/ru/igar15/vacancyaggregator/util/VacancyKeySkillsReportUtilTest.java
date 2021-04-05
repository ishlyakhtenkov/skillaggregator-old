package ru.igar15.vacancyaggregator.util;

import org.junit.jupiter.api.Test;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.to.VacancyKeySkillsReportTo;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VacancyKeySkillsReportUtilTest {

    @Test
    void getTo() {
        String keySkillsTestData = "JAVA=%=100 %\n" +
                "SPRING FRAMEWORK=%=49 %\n" +
                "GIT=%=40 %\n" +
                "POSTGRESQL=%=31 %";
        VacancyKeySkillsReport reportTestData = new VacancyKeySkillsReport(100000, "Java",
                "Moscow", LocalDate.now(), 2, 100, keySkillsTestData);

        Map<String, String> expectedKeySkillsMap = Map.of("JAVA", "100 %", "SPRING FRAMEWORK", "49 %",
                "GIT", "40 %", "POSTGRESQL", "31 %");
        VacancyKeySkillsReportTo expectedReportTo = new VacancyKeySkillsReportTo(100000, "Java",
                "Moscow", LocalDate.now(), 2, 100, expectedKeySkillsMap);

        VacancyKeySkillsReportTo actualReportTo = VacancyKeySkillsReportUtil.getTo(reportTestData);
        assertEquals(expectedReportTo, actualReportTo);
    }
}