package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class SkillsAggregatorV2 extends AbstractSkillsAggregator {

    @Override
    public void getAllVacanciesSkills(String hhRuUrl, int selection, boolean isVacancyPropertiesExist,
                                      Properties vacancyProperties, Map<String, Integer> skillsMap,
                                      int[] vacanciesAmount) throws IOException {
        List<String> vacanciesUrl= null;
        for (int i = 0; i < selection; i++) {
            vacanciesUrl = getVacanciesUrl(hhRuUrl, i);
            if (vacanciesUrl.size() == 0) {
                break;
            }
            int vacancyUrlCounter = 0;
            for (String vacancyUrl : vacanciesUrl) {
                vacancyUrlCounter++;
                if (vacancyUrlCounter > 50) {
                    break;
                }
                getVacancySkills(vacancyUrl, isVacancyPropertiesExist, vacancyProperties, skillsMap, vacanciesAmount);
            }
        }
    }

    private List<String> getVacanciesUrl(String url, int pageNumber) throws IOException {
        Document vacanciesPage = null;
        List<String> vacanciesUrl = null;
        for (int i = 0; i < 10; i++) {
            vacanciesPage = documentCreator.getDocument(String.format(url, pageNumber));
            vacanciesUrl = htmlParser.getVacanciesUrl(vacanciesPage);
            if (vacanciesUrl.size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return vacanciesUrl;
    }
}