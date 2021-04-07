package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class HhSkillsHtmlParser implements SkillsHtmlParser {

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