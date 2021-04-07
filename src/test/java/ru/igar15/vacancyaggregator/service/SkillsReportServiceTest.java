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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
}