package ru.igar15.skillsaggregator.aggregator;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.igar15.skillsaggregator.config.AppConfig;
import ru.igar15.skillsaggregator.model.SkillReport;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringJUnitConfig(AppConfig.class)
@ExtendWith(MockitoExtension.class)
class ToDeleteTest {

//    @Autowired
//    @InjectMocks
//    private SkillsAggregatorV1 aggregator;
//
//    @Mock
//    private DocumentCreator documentCreator;
//
//    @Test
//    void getReport() throws IOException {
//        setupMockHtmlDocumentCreator();
//        Optional<SkillReport> report = aggregator.getReport("java", "moscow", 1);
//        assertThat(report.get()).usingRecursiveComparison().isEqualTo(aggregatorReport);
//    }
//
//    @Test
//    void getReportWhenVacanciesNotFound() throws IOException {
//        Mockito.when(documentCreator.getDocument("https://hh.ru/search/vacancy?text=zzzzz+moscow&search_field=name&page=0"))
//                .thenReturn(Jsoup.parse(new File("src/test/resources/notfound.html"), "UTF-8"));
//        Optional<SkillReport> report = aggregator.getReport("zzzzz", "moscow", 1);
//        assertFalse(report.isPresent());
//    }
//
//    private void setupMockHtmlDocumentCreator() throws IOException {
//        Mockito.when(documentCreator.getDocument("https://hh.ru/search/vacancy?text=java+moscow&search_field=name&page=0"))
//                .thenReturn(Jsoup.parse(new File("src/test/resources/first.html"), "UTF-8"));
//        String urlRegex = "https://hh.ru/vacancy/%s";
//        String fileRegex = "src/test/resources/html/%s.html";
//        File file = new File("src/test/resources/html");
//        String[] fileNames = file.list();
//        for (String fileName : fileNames) {
//            fileName = fileName.replaceAll("\\.html", "");
//            Mockito.when(documentCreator.getDocument(String.format(urlRegex, fileName)))
//                    .thenReturn(Jsoup.parse(new File(String.format(fileRegex, fileName)), "UTF-8"));
//        }
//    }
}