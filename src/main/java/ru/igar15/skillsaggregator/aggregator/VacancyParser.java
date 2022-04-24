package ru.igar15.skillsaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VacancyParser {
    private static final String VACANCY_PAGE_URL_PATTERN = "https://hh.ru/vacancy/%s";
    private static final String VACANCY_ATTRIBUTE_KEY = "data-qa";
    private static final String VACANCY_TITLE_ATTRIBUTE_VALUE = "vacancy-serp__vacancy-title";
    private static final String TITLE = "title";
    private static final String HREF_ATTRIBUTE_KEY = "href";
    private static final String VACANCY_ID_ATTRIBUTE = "\"vacancyId\": ";
    private static final String VACANCY_SKILL_ATTRIBUTE_VALUE = "bloko-tag__text";

    public Elements getVacancies(Document vacanciesPage) {
        return vacanciesPage.getElementsByAttributeValue(VACANCY_ATTRIBUTE_KEY, VACANCY_TITLE_ATTRIBUTE_VALUE);
    }

    public String getVacancyUrl(Element vacancy) {
        return vacancy.getElementsByAttributeValueContaining(VACANCY_ATTRIBUTE_KEY, TITLE).attr(HREF_ATTRIBUTE_KEY);
    }

    public List<String> getVacanciesUrl(Document vacanciesPage) {
        String vacanciesPageText = vacanciesPage.toString();
        String[] vacanciesIdLines = vacanciesPageText.split(VACANCY_ID_ATTRIBUTE);
        List<String> vacanciesUrl = new ArrayList<>();
        for (String vacancyIdLine : vacanciesIdLines) {
            if (isValidVacancyIdLine(vacancyIdLine)) {
                vacanciesUrl.add(String.format(VACANCY_PAGE_URL_PATTERN, vacancyIdLine.split(",")[0]));
            }
        }
        return vacanciesUrl;
    }

    private boolean isValidVacancyIdLine(String vacancyId) {
        return vacancyId.matches("^[0-9][A-Za-z0-9]*$");
    }

    public Elements getVacancySkills(Document vacancyPage) {
        return vacancyPage.getElementsByAttributeValue(VACANCY_ATTRIBUTE_KEY, VACANCY_SKILL_ATTRIBUTE_VALUE);
    }
}