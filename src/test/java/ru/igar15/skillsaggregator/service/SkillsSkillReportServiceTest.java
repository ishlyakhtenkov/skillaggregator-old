package ru.igar15.skillsaggregator.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.igar15.skillsaggregator.config.AppConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(AppConfig.class)
@ExtendWith(MockitoExtension.class)
@Transactional
@Sql(scripts = "classpath:db/init_db.sql", config = @SqlConfig(encoding = "UTF-8"))
class SkillsSkillReportServiceTest {

//    @Autowired
//    @InjectMocks
//    private SkillReportService service;
//
//    @Autowired
//    private SkillReportRepository repository;
//
//    @Mock
//    private Aggregator<SkillReport> aggregator;
//
//    @Test
//    void getReportTodayFromRepo() throws IOException {
//        SkillReport created = repository.save(getNew());
//        int newId = created.getId();
//        SkillReport newReport = getNew();
//        newReport.setId(newId);
//        assertThat(created).usingRecursiveComparison().isEqualTo(newReport);
//        assertThat(service.getSkillReportForToday("name", "city", 2).get()).usingRecursiveComparison().isEqualTo(newReport);
//    }
//
//    @Test
//    void getTodayFromAggregator() throws IOException {
//        Mockito.when(aggregator.getReport("NAME", "CITY", 2)).thenReturn(Optional.of(getNew()));
//
//        SkillReport created = service.getSkillReportForToday("name", "city", 2).get();
//        int newId = created.getId();
//        SkillReport newReport = getNew();
//        newReport.setId(newId);
//        assertThat(created).usingRecursiveComparison().isEqualTo(newReport);
//        assertThat(repository.findByProfessionNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).get())
//                .usingRecursiveComparison().isEqualTo(created);
//    }
//
//    @Test
//    void getTodayFromAggregatorWithZeroVacancies() throws IOException {
//        Mockito.when(aggregator.getReport("NAME", "CITY", 2)).thenReturn(Optional.empty());
//
//        Optional<SkillReport> report = service.getSkillReportForToday("name", "city", 2);
//        assertFalse(report.isPresent());
//        assertFalse(repository.findByProfessionNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
//    }
//
//    @Test
//    void getAllReportsToday() {
//        SkillReport report1 = getNew();
//        report1.setProfessionName("report1");
//        SkillReport report2 = getNew();
//        report1.setProfessionName("report2");
//        repository.save(report1);
//        repository.save(report2);
//        assertThat(service.getAllSkillReportsForToday()).usingRecursiveComparison().isEqualTo(List.of(report1, report2));
//    }
//
//    @Test
//    void deleteReportToday() {
//        repository.save(getNew());
//        assertTrue(repository.findByProfessionNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
//        service.deleteSkillReportForToday("name", "city", 2);
//        assertFalse(repository.findByProfessionNameAndCityAndDateAndSelection("NAME", "CITY", LocalDate.now(), 2).isPresent());
//    }
//
//    @Test
//    void deleteAllReportsToday() {
//        SkillReport report1 = getNew();
//        report1.setProfessionName("report1");
//        SkillReport report2 = getNew();
//        report2.setProfessionName("report2");
//        repository.save(report1);
//        repository.save(report2);
//        assertTrue(repository.findByProfessionNameAndCityAndDateAndSelection("report1", "CITY", LocalDate.now(), 2).isPresent());
//        assertTrue(repository.findByProfessionNameAndCityAndDateAndSelection("report2", "CITY", LocalDate.now(), 2).isPresent());
//        service.deleteAllSkillReportsForToday();
//        assertFalse(repository.findByProfessionNameAndCityAndDateAndSelection("report1", "CITY", LocalDate.now(), 2).isPresent());
//        assertFalse(repository.findByProfessionNameAndCityAndDateAndSelection("report1", "CITY", LocalDate.now(), 2).isPresent());
//    }
}