package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public interface VacancyKeySkillsHtmlParser {

    int getPagesAmount(Document document);

    Elements getVacancyElements(Document document);

    String getVacancyUrl(Element vacancy);

    Elements getVacancyKeySkills(Document vacancyPage);
}
