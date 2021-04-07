package ru.igar15.vacancyaggregator.util;

import org.junit.jupiter.api.Test;
import ru.igar15.vacancyaggregator.SkillsReportTestData;
import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.to.SkillsReportTo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SkillsReportMapSortUtilTest {

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
        report.setKeySkills("");
        SkillsReportTo reportTo = SkillsReportUtil.getTo(report);
        SkillsReportTo expectedReportTo = new SkillsReportTo(100000, "NAME",
                "CITY", LocalDate.now(), 2, 100, new LinkedHashMap<>());
        assertEquals(expectedReportTo, reportTo);
    }

    @Test
    void getToWhenDelimiterNotExist() {
        SkillsReport report = SkillsReportTestData.getNew();
        report.setId(100000);
        report.setKeySkills("JAVA=100 %\nSpring=50 %");
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
        expectedReport.setVacanciesAmount(0);
        expectedReport.setKeySkills("");
        Map<String, Integer> keySkills = Map.of("JAVA", 100, "SPRING FRAMEWORK", 49,
                "GIT", 40, "POSTGRESQL", 31);

        SkillsReport report = SkillsReportUtil.get("NAME", "CITY", 2, 0, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(expectedReport);
    }

    @Test
    void getWhenKeySkillsEmpty() {
        SkillsReport expectedReport = SkillsReportTestData.getNew();
        expectedReport.setVacanciesAmount(100);
        expectedReport.setKeySkills("");
        Map<String, Integer> keySkills = new HashMap<>();

        SkillsReport report = SkillsReportUtil.get("NAME", "CITY", 2, 100, keySkills);
        assertThat(report).usingRecursiveComparison().isEqualTo(expectedReport);
    }
}