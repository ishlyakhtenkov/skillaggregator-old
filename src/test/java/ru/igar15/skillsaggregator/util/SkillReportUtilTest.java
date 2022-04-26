package ru.igar15.skillsaggregator.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.skillReport1;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.skillReportTo;

class SkillReportUtilTest {

    @Test
    void createTo() {
        assertThat(SkillReportUtil.createTo(skillReport1)).usingRecursiveComparison().isEqualTo(skillReportTo);
    }
}