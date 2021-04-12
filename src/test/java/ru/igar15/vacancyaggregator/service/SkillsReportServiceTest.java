package ru.igar15.vacancyaggregator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.igar15.vacancyaggregator.aggregator.Aggregator;
import ru.igar15.vacancyaggregator.config.AppConfig;
import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.repository.SkillsReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.igar15.vacancyaggregator.SkillsReportTestData.getNew;

@SpringJUnitConfig(AppConfig.class)
@ExtendWith(MockitoExtension.class)
@Transactional
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class SkillsReportServiceTest {

    @Autowired
    @InjectMocks
    private SkillsReportService service;

    @Autowired
    private SkillsReportRepository repository;

    @Mock
    private Aggregator<SkillsReport> aggregator;

    @Test
    void getReportTodayFromRepo() throws IOException {
        SkillsReport created = repository.save(getNew());
        int newId = created.getId();
        SkillsReport newReport = getNew();
        newReport.setId(newId);
        assertThat(created).usingRecursiveComparison().isEqualTo(newReport);
        assertThat(service.getReportToday("name", "city", 2).get()).usingRecursiveComparison().isEqualTo(newReport);
    }

    @Test
    void getTodayFromAggregator() throws IOException {
        Mockito.when(aggregator.getReport("NAME", "CITY", 2)).thenReturn(Optional.of(getNew()));

        SkillsReport created = service.getReportToday("name", "city", 2).get();
        int newId = created.getId();
        SkillsReport newReport = getNew();
        newReport.setId(newId);
        assertThat(created).usingRecursiveComparison().isEqualTo(newReport);
        assertThat(repository.findByNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).get())
                .usingRecursiveComparison().isEqualTo(created);
    }

    @Test
    void getTodayFromAggregatorWithZeroVacancies() throws IOException {
        Mockito.when(aggregator.getReport("NAME", "CITY", 2)).thenReturn(Optional.empty());

        Optional<SkillsReport> report = service.getReportToday("name", "city", 2);
        assertFalse(report.isPresent());
        assertFalse(repository.findByNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
    }

    @Test
    void getAllReportsToday() {
        SkillsReport report1 = getNew();
        report1.setName("report1");
        SkillsReport report2 = getNew();
        report1.setName("report2");
        repository.save(report1);
        repository.save(report2);
        assertThat(service.getAllReportsToday()).usingRecursiveComparison().isEqualTo(List.of(report1, report2));
    }

    @Test
    void deleteReportToday() {
        repository.save(getNew());
        assertTrue(repository.findByNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
        service.deleteReportToday("name", "city", 2);
        assertFalse(repository.findByNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
    }

    @Test
    void deleteAllReportsToday() {
        SkillsReport report1 = getNew();
        report1.setName("report1");
        SkillsReport report2 = getNew();
        report2.setName("report2");
        repository.save(report1);
        repository.save(report2);
        assertTrue(repository.findByNameAndCityAndDateAndSelection("report1", "CITY", LocalDate.now(), 2).isPresent());
        assertTrue(repository.findByNameAndCityAndDateAndSelection("report2", "CITY", LocalDate.now(), 2).isPresent());
        service.deleteAllReportsToday();
        assertFalse(repository.findByNameAndCityAndDateAndSelection("report1", "CITY", LocalDate.now(), 2).isPresent());
        assertFalse(repository.findByNameAndCityAndDateAndSelection("report1", "CITY", LocalDate.now(), 2).isPresent());
    }
}