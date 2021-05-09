package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;


public interface SkillsHtmlParser {

    Elements getVacancies(Document vacanciesPage);

    String getVacancyUrl(Element vacancy);

    List<String> getVacanciesUrl(Document vacanciesPage);

    Elements getVacancyKeySkills(Document vacancyPage);
}
