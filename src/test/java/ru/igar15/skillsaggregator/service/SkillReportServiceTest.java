package ru.igar15.skillsaggregator.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.igar15.skillsaggregator.aggregator.HtmlPageLoader;
import ru.igar15.skillsaggregator.config.AppConfig;
import ru.igar15.skillsaggregator.model.SkillReport;
import ru.igar15.skillsaggregator.repository.SkillReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.igar15.skillsaggregator.model.Selection.FIRST_100_VACANCIES;
import static ru.igar15.skillsaggregator.testdata.SkillReportTestData.*;

@SpringJUnitConfig(AppConfig.class)
@Sql(scripts = "classpath:db/init_db.sql", config = @SqlConfig(encoding = "UTF-8"))
class SkillReportServiceTest {
    private static MockedStatic<HtmlPageLoader> mockedPageLoader;

    @Autowired
    private SkillReportService service;

    @Autowired
    private SkillReportRepository repository;


    @BeforeAll
    static void setupMock() throws IOException {
        mockedPageLoader = mockHtmlPageLoader();
    }

    @AfterAll
    static void destroyMock() throws IOException {
        mockedPageLoader.close();
    }

    @Test
    void getSkillReportForTodayWhenFoundInDb() throws IOException {
        SkillReport dbSkillReport = service.getSkillReportForToday(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        assertThat(dbSkillReport).usingRecursiveComparison().isEqualTo(skillReport1);
    }

    @Test
    void getSkillReportForTodayWhenNotFoundInDb() throws IOException {
        repository.deleteByNameAndCityAndDateAndSelection(JAVA_PROFESSION_NAME, MOSCOW_CITY, LocalDate.now(), FIRST_100_VACANCIES);
        SkillReport skillReport = service.getSkillReportForToday(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        assertThat(skillReport).usingRecursiveComparison().ignoringFields("id").isEqualTo(vacancyTestPagesSkillReport);
    }

    @Test
    void getSkillReportWhenNoVacanciesFound() throws IOException {
        assertThrows(EmptySkillReportException.class,
                () -> service.getSkillReportForToday(NOT_EXISTED_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES));
    }

    @Test
    void getAllSkillReportsForToday() {
        List<SkillReport> skillReports = service.getAllSkillReportsForToday();
        assertThat(skillReports).usingRecursiveComparison().isEqualTo(List.of(skillReport1, skillReport2));
    }

    @Test
    void deleteSkillReportForToday() {
        service.deleteSkillReportForToday(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        assertFalse(repository.findByProfessionNameAndCityAndDateAndSelection(JAVA_PROFESSION_NAME, MOSCOW_CITY,
                LocalDate.now(), FIRST_100_VACANCIES).isPresent());
    }

    @Test
    void deleteAllSkillReportsForToday() {
        service.deleteAllSkillReportsForToday();
        assertTrue(service.getAllSkillReportsForToday().isEmpty());
    }
}