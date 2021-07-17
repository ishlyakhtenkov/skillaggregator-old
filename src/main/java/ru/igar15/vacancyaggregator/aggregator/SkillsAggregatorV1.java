package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Component
public class SkillsAggregatorV1 extends AbstractSkillsAggregator {

    @Override
    public void getAllVacanciesSkills(String hhRuUrl, int selection, boolean isVacancyPropertiesExist,
                                      Properties vacancyProperties, Map<String, Integer> skillsMap,
                                      int[] vacanciesAmount) throws IOException {
        Elements vacancies = null;
        for (int i = 0; i < selection; i++) {
            vacancies = getVacancies(hhRuUrl, i);
            if (vacancies.size() == 0) {
                break;
            }
            for (Element vacancy : vacancies) {
                String vacancyUrl = htmlParser.getVacancyUrl(vacancy);
                getVacancySkills(vacancyUrl, isVacancyPropertiesExist, vacancyProperties, skillsMap, vacanciesAmount);
            }
        }
    }

    private Elements getVacancies(String url, int pageNumber) throws IOException {
        Document vacanciesPage = null;
        Elements vacancies = null;
        for (int i = 0; i < 10; i++) {
            vacanciesPage = documentCreator.getDocument(String.format(url, pageNumber));
            vacancies = htmlParser.getVacancies(vacanciesPage);
            if (vacancies.size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return vacancies;
    }
}