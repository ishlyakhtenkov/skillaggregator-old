package ru.igar15.vacancyaggregator.aggregator;

import java.io.IOException;
import java.util.Optional;

public interface Aggregator<T> {
    Optional<T> getReport(String name, String city, int selection) throws IOException;
}
