package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HhSkillsParserTest {
    private final Document vacanciesPage = Jsoup.parse(new File("src/test/resources/first.html"), "UTF-8");

    private final SkillsParser htmlParser = new HhSkillsParser();

    HhSkillsParserTest() throws IOException {
    }

    @Test
    void getVacancies() {
        Elements vacancies = htmlParser.getVacancies(vacanciesPage);
        assertEquals(50, vacancies.size());
    }

    @Test
    void getVacancyUrl() {
        Elements vacancies = htmlParser.getVacancies(vacanciesPage);
        String vacancyUrl = htmlParser.getVacancyUrl(vacancies.get(2));
        assertEquals("https://hh.ru/vacancy/43112275", vacancyUrl);
    }

    @Test
    void getVacancyKeySkills() throws IOException {
        Document vacancyPage = Jsoup.parse(new File("src/test/resources/html/43570368.html"), "UTF-8");
        Elements vacancyKeySkills = htmlParser.getVacancySkills(vacancyPage);
        String keySkills = vacancyKeySkills.stream().map(Element::text).collect(Collectors.joining(", "));
        String expectedKeySkills = "Английский — B2 — Средне-продвинутый, Spring Framework, SQL, Linux, Java, Английский язык";
        assertEquals(expectedKeySkills, keySkills);
    }
}