package ru.igar15.vacancyaggregator.aggregator;

import ru.igar15.vacancyaggregator.model.VacancyReport;

import java.io.IOException;

public interface VacancyAggregator {
    VacancyReport getAggregationResult(String name, String city, int selection) throws IOException;
}
