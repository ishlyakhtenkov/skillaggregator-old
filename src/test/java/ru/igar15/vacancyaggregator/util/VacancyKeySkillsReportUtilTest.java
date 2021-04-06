package ru.igar15.vacancyaggregator.util;

import org.junit.jupiter.api.Test;
import ru.igar15.vacancyaggregator.VacancyKeySkillsReportTestData;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.to.VacancyKeySkillsReportTo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VacancyKeySkillsReportUtilTest {

    @Test
    void getTo() {
        Map<String, String> expectedKeySkillsMap = Map.of("JAVA", "100 %", "SPRING FRAMEWORK", "49 %",
                "GIT", "40 %", "POSTGRESQL", "31 %");
        VacancyKeySkillsReportTo expectedReportTo = new VacancyKeySkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, expectedKeySkillsMap);

        VacancyKeySkillsReport report = VacancyKeySkillsReportTestData.getNew();
        report.setId(100000);
        VacancyKeySkillsReportTo reportTo = VacancyKeySkillsReportUtil.getTo(report);
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void getToWhenEmptyKeySkills() {
        VacancyKeySkillsReport report = VacancyKeySkillsReportTestData.getNew();
        report.setId(100000);
        report.setKeySkills("");
        VacancyKeySkillsReportTo reportTo = VacancyKeySkillsReportUtil.getTo(report);
        VacancyKeySkillsReportTo expectedReportTo = new VacancyKeySkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, new LinkedHashMap<>());
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void getToWhenDelimiterNotExist() {
        VacancyKeySkillsReport report = VacancyKeySkillsReportTestData.getNew();
        report.setId(100000);
        report.setKeySkills("JAVA=100 %\nSpring=50 %");
        VacancyKeySkillsReportTo reportTo = VacancyKeySkillsReportUtil.getTo(report);
        VacancyKeySkillsReportTo expectedReportTo = new VacancyKeySkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, new LinkedHashMap<>());
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void get() {
        Map<String, Integer> keySkills = Map.of("JAVA", 100, "SPRING FRAMEWORK", 49,
                "GIT", 40, "POSTGRESQL", 31);

        VacancyKeySkillsReport report = VacancyKeySkillsReportUtil.get("NAME", "CITY", 2, 100, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(VacancyKeySkillsReportTestData.report);
    }

    @Test
    void getWhenVacanciesAmountZero() {
        VacancyKeySkillsReport expectedReport = VacancyKeySkillsReportTestData.getNew();
        expectedReport.setVacanciesAmount(0);
        expectedReport.setKeySkills("");
        Map<String, Integer> keySkills = Map.of("JAVA", 100, "SPRING FRAMEWORK", 49,
                "GIT", 40, "POSTGRESQL", 31);

        VacancyKeySkillsReport report = VacancyKeySkillsReportUtil.get("NAME", "CITY", 2, 0, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(expectedReport);
    }

    @Test
    void getWhenKeySkillsEmpty() {
        VacancyKeySkillsReport expectedReport = VacancyKeySkillsReportTestData.getNew();
        expectedReport.setVacanciesAmount(100);
        expectedReport.setKeySkills("");
        Map<String, Integer> keySkills = new HashMap<>();

        VacancyKeySkillsReport report = VacancyKeySkillsReportUtil.get("NAME", "CITY", 2, 100, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(expectedReport);
    }
}