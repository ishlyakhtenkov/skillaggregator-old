package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
class VacancyKeySkillsAggregatorTest {

    private VacancyKeySkillsAggregator aggregator;

    @Test
    void getReport() throws IOException {
        File file = new File("src/test/resources/first.html");
        Document document = Jsoup.parse(file, "UTF-8", "https://hh.ru/");
        System.out.println();
    }
}