package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HhVacancyKeySkillsHtmlParserTest {
    private final Document vacanciesPage = Jsoup.parse(new File("src/test/resources/first.html"), "UTF-8");

    private final VacancyKeySkillsHtmlParser htmlParser = new HhVacancyKeySkillsHtmlParser();

    HhVacancyKeySkillsHtmlParserTest() throws IOException {
    }

    @Test
    void getPagesAmount() {
        int pagesAmount = htmlParser.getPagesAmount(vacanciesPage);
        assertEquals(40, pagesAmount);
    }

    @Test
    void getVacancies() {
        Elements vacancies = htmlParser.getVacancies(vacanciesPage);
        System.out.println();
    }

    @Test
    void getVacancyUrl() {
        StringBuilder builder = new StringBuilder();
        System.out.println(builder.toString().trim());
    }

    @Test
    void getVacancyKeySkills() throws IOException {
        WebHtmlDocumentCreator creator = new WebHtmlDocumentCreator();
        Document document = creator.getDocument("https://hh.ru/search/vacancy?text=дворник+химки&page=3");
        Elements vacancies = htmlParser.getVacancies(document);
        System.out.println();
    }
}