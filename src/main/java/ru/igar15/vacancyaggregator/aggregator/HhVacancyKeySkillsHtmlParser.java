package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

@Component
public class HhVacancyKeySkillsHtmlParser implements VacancyKeySkillsHtmlParser {

    @Override
    public int getPagesAmount(Document document) {
        //            Elements elements = document.getElementsByClass("bloko-button HH-Pager-Control");
        Elements pageNumbersElements = document.getElementById("HH-React-Root")
                .getElementsByAttributeValueContaining("href", "/search/vacancy");
        Element lastPageNumber = pageNumbersElements.get(pageNumbersElements.size() - 2);
        Elements lastPageNumberElements = lastPageNumber.getElementsByClass("bloko-button");

        OptionalInt max = lastPageNumberElements.stream()
                .mapToInt(lastPageNumberElement -> Integer.parseInt(lastPageNumberElement.text()))
                .max();
        return max.orElse(0);
    }

    @Override
    public Elements getVacancies(Document vacanciesPage) {
        return vacanciesPage.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title");
    }

    @Override
    public String getVacancyUrl(Element vacancy) {
        return vacancy.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
    }

    @Override
    public Elements getVacancyKeySkills(Document vacancyPage) {
        return vacancyPage.getElementsByAttributeValue("data-qa", "bloko-tag__text");
    }
}