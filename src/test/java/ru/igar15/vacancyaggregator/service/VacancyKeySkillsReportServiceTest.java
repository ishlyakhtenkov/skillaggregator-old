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
import ru.igar15.vacancyaggregator.aggregator.VacancyAggregator;
import ru.igar15.vacancyaggregator.config.AppConfig;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.repository.VacancyKeySkillsReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.igar15.vacancyaggregator.VacancyKeySkillsReportTestData.getNew;

@SpringJUnitConfig(AppConfig.class)
@ExtendWith(MockitoExtension.class)
@Transactional
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VacancyKeySkillsReportServiceTest {

    @Autowired
    @InjectMocks
    private VacancyKeySkillsReportService service;

    @Autowired
    private VacancyKeySkillsReportRepository repository;

    @Mock
    private VacancyAggregator<VacancyKeySkillsReport> aggregator;

    @Test
    void getReportTodayFromRepo() throws IOException {
        VacancyKeySkillsReport created = repository.save(getNew());
        int newId = created.getId();
        VacancyKeySkillsReport newReport = getNew();
        newReport.setId(newId);
        assertThat(created).usingRecursiveComparison().isEqualTo(newReport);
        assertThat(service.getReportToday("name", "city", 2).get()).usingRecursiveComparison().isEqualTo(newReport);
    }

    @Test
    void getTodayFromAggregator() throws IOException {
        Mockito.when(aggregator.getReport("NAME", "CITY", 2)).thenReturn(Optional.of(getNew()));

        VacancyKeySkillsReport created = service.getReportToday("name", "city", 2).get();
        int newId = created.getId();
        VacancyKeySkillsReport newReport = getNew();
        newReport.setId(newId);
        assertThat(created).usingRecursiveComparison().isEqualTo(newReport);
        assertThat(repository.findByNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).get())
                .usingRecursiveComparison().isEqualTo(created);
    }

    @Test
    void getTodayFromAggregatorWithZeroVacancies() throws IOException {
        Mockito.when(aggregator.getReport("NAME", "CITY", 2)).thenReturn(Optional.empty());

        Optional<VacancyKeySkillsReport> report = service.getReportToday("name", "city", 2);
        assertFalse(report.isPresent());
        assertFalse(repository.findByNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
    }
}