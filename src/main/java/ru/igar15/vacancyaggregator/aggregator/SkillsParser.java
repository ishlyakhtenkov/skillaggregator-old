package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;


public interface SkillsParser {

    Elements getVacancies(Document vacanciesPage);

    String getVacancyUrl(Element vacancy);

    List<String> getVacanciesUrl(Document vacanciesPage);

    Elements getVacancySkills(Document vacancyPage);
}