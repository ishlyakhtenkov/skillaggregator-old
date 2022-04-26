package ru.igar15.skillsaggregator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.igar15.skillsaggregator.config.AppConfig;
import ru.igar15.skillsaggregator.model.Selection;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.igar15.skillsaggregator.model.Selection.FIRST_100_VACANCIES;

@SpringJUnitConfig(AppConfig.class)
@Sql(scripts = "classpath:db/init_db.sql", config = @SqlConfig(encoding = "UTF-8"))
class SkillReportServiceTest {

    @Autowired
    private SkillReportService service;

    @Test
    void getSkillReportForToday() throws IOException {
        service.getSkillReportForToday("java", "moscow", FIRST_100_VACANCIES);
    }

    @Test
    void getAllSkillReportsForToday() {
    }

    @Test
    void deleteSkillReportForToday() {
    }

    @Test
    void deleteAllSkillReportsForToday() {
    }
}