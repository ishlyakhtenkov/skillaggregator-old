package ru.igar15.skillsaggregator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.skillReport;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.skillReportTo;

class SkillReportUtilTest {

    @Test
    void createTo() {
        assertEquals(skillReportTo, SkillReportUtil.createTo(skillReport));
    }

}