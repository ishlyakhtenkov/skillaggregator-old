package ru.igar15.skillsaggregator.aggregator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import ru.igar15.skillsaggregator.model.SkillReport;

import java.io.IOException;

import static ru.igar15.skillsaggregator.model.Selection.FIRST_100_VACANCIES;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.*;

class SkillAggregatorTest {
    private static MockedStatic<HtmlPageLoader> mockedPageLoader;

    @BeforeAll
    static void setupMock() throws IOException {
        mockedPageLoader = mockHtmlPageLoader();
    }

    @AfterAll
    static void destroyMock() throws IOException {
        mockedPageLoader.close();
    }

    @Test
    void makeSkillReport() throws IOException {
        SkillAggregator skillAggregator = new SkillAggregator(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        SkillReport skillReport = skillAggregator.makeSkillReport();
        Assertions.assertThat(skillReport).usingRecursiveComparison().isEqualTo(vacancyTestPagesSkillReport);
    }
}