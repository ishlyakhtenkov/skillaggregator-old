package ru.igar15.skillsaggregator.util;

import org.junit.jupiter.api.Test;
import ru.igar15.skillsaggregator.SkillsReportTestData;
import ru.igar15.skillsaggregator.model.SkillsReport;
import ru.igar15.skillsaggregator.to.SkillsReportTo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SkillsSkillsReportMapSortUtilTest {

    @Test
    void getTo() {
        Map<String, String> expectedKeySkillsMap = Map.of("JAVA", "100 %", "SPRING FRAMEWORK", "49 %",
                "GIT", "40 %", "POSTGRESQL", "31 %");
        SkillsReportTo expectedReportTo = new SkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, expectedKeySkillsMap);

        SkillsReport report = SkillsReportTestData.getNew();
        report.setId(100000);
        SkillsReportTo reportTo = SkillsReportUtil.getTo(report);
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void getToWhenEmptyKeySkills() {
        SkillsReport report = SkillsReportTestData.getNew();
        report.setId(100000);
        report.setRequiredSkills("");
        SkillsReportTo reportTo = SkillsReportUtil.getTo(report);
        SkillsReportTo expectedReportTo = new SkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, new LinkedHashMap<>());
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void getToWhenDelimiterNotExist() {
        SkillsReport report = SkillsReportTestData.getNew();
        report.setId(100000);
        report.setRequiredSkills("JAVA=100 %\nSpring=50 %");
        SkillsReportTo reportTo = SkillsReportUtil.getTo(report);
        SkillsReportTo expectedReportTo = new SkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, new LinkedHashMap<>());
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void get() {
        Map<String, Integer> keySkills = Map.of("JAVA", 100, "SPRING FRAMEWORK", 49,
                "GIT", 40, "POSTGRESQL", 31);

        SkillsReport report = SkillsReportUtil.get("NAME", "CITY", 2, 100, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(SkillsReportTestData.report);
    }

    @Test
    void getWhenVacanciesAmountZero() {
        SkillsReport expectedReport = SkillsReportTestData.getNew();
        expectedReport.setAnalyzedVacanciesAmount(0);
        expectedReport.setRequiredSkills("");
        Map<String, Integer> keySkills = Map.of("JAVA", 100, "SPRING FRAMEWORK", 49,
                "GIT", 40, "POSTGRESQL", 31);

        SkillsReport report = SkillsReportUtil.get("NAME", "CITY", 2, 0, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(expectedReport);
    }

    @Test
    void getWhenKeySkillsEmpty() {
        SkillsReport expectedReport = SkillsReportTestData.getNew();
        expectedReport.setAnalyzedVacanciesAmount(100);
        expectedReport.setRequiredSkills("");
        Map<String, Integer> keySkills = new HashMap<>();

        SkillsReport report = SkillsReportUtil.get("NAME", "CITY", 2, 100, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(expectedReport);
    }
}