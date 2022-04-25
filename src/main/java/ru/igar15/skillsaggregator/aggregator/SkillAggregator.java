package ru.igar15.skillsaggregator.aggregator;

import org.jsoup.nodes.Document;
import ru.igar15.skillsaggregator.model.Selection;
import ru.igar15.skillsaggregator.model.SkillReport;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.igar15.skillsaggregator.aggregator.HtmlPageLoader.*;

public class SkillAggregator {
    private static final String VACANCIES_PAGE_URL_PATTERN = "https://hh.ru/search/vacancy?text=%s+%s&search_field=name&page=%d";
    private static final int VACANCIES_AMOUNT_PER_PAGE = 50;
    private static final String VACANCY_PAGE_URL_PATTERN = "https://hh.ru/vacancy/%s";
    private static final String VACANCY_ATTRIBUTE_KEY = "data-qa";
    private static final String VACANCY_TITLE_ATTRIBUTE_VALUE = "vacancy-serp__vacancy-title";
    private static final String TITLE = "title";
    private static final String HREF_ATTRIBUTE_KEY = "href";
    private static final String VACANCY_ID_ATTRIBUTE = "\"vacancyId\": ";
    private static final String VACANCY_ID_PATTERN = "^[0-9][A-Za-z0-9]*$";
    private static final String VACANCY_SKILL_ATTRIBUTE_VALUE = "bloko-tag__text";
    private static final String VACANCY_ID_LINE_SPLITTER = ",";

    private final String professionName;
    private final String city;
    private final Selection selection;
    private int analyzedVacanciesAmount;
    private final Map<String, Integer> skillCounter;

    public SkillAggregator(String professionName, String city, Selection selection) {
        this.professionName = professionName;
        this.city = city;
        this.selection = selection;
        this.skillCounter = new HashMap<>();
    }

    public SkillReport makeSkillReport() throws IOException {
        int pagesAmount = selection.getVacanciesAmount() / VACANCIES_AMOUNT_PER_PAGE;
        for (int pageNumber = 0; pageNumber < pagesAmount; pageNumber++) {
            String vacanciesPageUrl = String.format(VACANCIES_PAGE_URL_PATTERN, professionName, city, pageNumber);
            Document vacanciesPage = loadPage(vacanciesPageUrl);
            handleVacanciesPage(vacanciesPage);
        }
        return new SkillReport(professionName, city, analyzedVacanciesAmount, skillCounter, selection);
    }

    private void handleVacanciesPage(Document vacanciesPage) throws IOException {
        Set<String> vacanciesUrls = getVacanciesUrls(vacanciesPage);
        for (String vacancyUrl : vacanciesUrls) {
            Document vacancyPage = loadPage(vacancyUrl);
            aggregateVacancySkills(vacancyPage);
        }
    }

    private Set<String> getVacanciesUrls(Document vacanciesPage) {
        String vacanciesPageContent = vacanciesPage.toString();
        return Arrays.stream(vacanciesPageContent.split(VACANCY_ID_ATTRIBUTE))
                .filter(this::isValidVacancyIdLine)
                .map(vacancyIdLine -> String.format(VACANCY_PAGE_URL_PATTERN, vacancyIdLine.split(VACANCY_ID_LINE_SPLITTER)[0]))
                .collect(Collectors.toSet());
    }

    private boolean isValidVacancyIdLine(String vacancyIdLine) {
        return vacancyIdLine.matches(VACANCY_ID_PATTERN);
    }

    private void aggregateVacancySkills(Document vacancyPage) {
        vacancyPage.getElementsByAttributeValue(VACANCY_ATTRIBUTE_KEY, VACANCY_SKILL_ATTRIBUTE_VALUE).stream()
                .map(vacancySkill -> vacancySkill.text().toUpperCase())
                .forEach(skill -> skillCounter.merge(skill, 1, Integer::sum));
        analyzedVacanciesAmount++;
    }


//    public Elements getVacancies(Document vacanciesPage) {
//        return vacanciesPage.getElementsByAttributeValue(VACANCY_ATTRIBUTE_KEY, VACANCY_TITLE_ATTRIBUTE_VALUE);
//    }

//    public String getVacancyUrl(Element vacancy) {
//        return vacancy.getElementsByAttributeValueContaining(VACANCY_ATTRIBUTE_KEY, TITLE).attr(HREF_ATTRIBUTE_KEY);
//    }





}