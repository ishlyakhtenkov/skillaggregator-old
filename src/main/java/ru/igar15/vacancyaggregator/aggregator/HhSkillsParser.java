package ru.igar15.vacancyaggregator.aggregator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HhSkillsParser implements SkillsParser {
    private static final String HH_RU_VACANCY_URL_SAMPLE = "https://hh.ru/vacancy/%s";

    @Override
    public Elements getVacancies(Document vacanciesPage) {
        return vacanciesPage.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title");
    }

    @Override
    public String getVacancyUrl(Element vacancy) {
        return vacancy.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
    }

    @Override
    public List<String> getVacanciesUrl(Document vacanciesPage) {
        String page = vacanciesPage.toString();
        String[] split = page.split("\"vacancyId\": ");
        List<String> vacanciesUrl = new ArrayList<>();
        for (String tempString : split) {
            if (isVacancyId(tempString)) {
                vacanciesUrl.add(String.format(HH_RU_VACANCY_URL_SAMPLE, tempString.split(",")[0]));
            }
        }
        return vacanciesUrl;
    }

    @Override
    public Elements getVacancySkills(Document vacancyPage) {
        return vacancyPage.getElementsByAttributeValue("data-qa", "bloko-tag__text");
    }

    private boolean isVacancyId(String line) {
        boolean isVacancyId = false;
        for (int i = 0; i < 10; i++) {
            isVacancyId = line.startsWith("" + i);
            if (isVacancyId) {
                break;
            }
        }
        return isVacancyId;
    }
}