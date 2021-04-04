package ru.igar15.vacancyaggregator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.igar15.vacancyaggregator.aggregators.VacancyAggregator;
import ru.igar15.vacancyaggregator.aggregators.VacancyKeySkillsAggregator;
import ru.igar15.vacancyaggregator.config.AppConfig;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.model.VacancyReport;
import ru.igar15.vacancyaggregator.service.VacancyKeySkillsReportService;

import java.io.IOException;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) throws IOException {
//        VacancyAggregator vacancyAggregator = new VacancyKeySkillsAggregator();
//        System.out.println(vacancyAggregator.getAggregationResult("Java", "Moscow"));


    }
}
