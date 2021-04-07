package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public interface SkillsHtmlParser {

    Elements getVacancies(Document vacanciesPage);

    String getVacancyUrl(Element vacancy);

    Elements getVacancyKeySkills(Document vacancyPage);
}
