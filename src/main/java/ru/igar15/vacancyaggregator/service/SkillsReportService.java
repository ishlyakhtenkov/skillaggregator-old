package ru.igar15.vacancyaggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.igar15.vacancyaggregator.aggregator.Aggregator;
import ru.igar15.vacancyaggregator.model.SkillsReport;
import ru.igar15.vacancyaggregator.repository.SkillsReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SkillsReportService {

    @Autowired
    private SkillsReportRepository repository;

    @Autowired
    @Qualifier("skillsAggregatorV2")
    private Aggregator<SkillsReport> vacancyKeySkillsAggregator;

    public Optional<SkillsReport> getReportToday(String name, String city, int selection) throws IOException {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(city, "city must not be null");
        Optional<SkillsReport> report = repository.findByNameAndCityAndDateAndSelection(name.toUpperCase(), city.toUpperCase(), LocalDate.now(), selection);
        if (report.isEmpty()) {
            report = vacancyKeySkillsAggregator.getReport(name.toUpperCase(), city.toUpperCase(), selection);
            if (report.isPresent()) {
                try {
                    repository.save(report.get());
                } catch (Exception e) {
                    return report;
                }
            }
        }
        return report;
    }

    public List<SkillsReport> getAllReportsToday() {
        return repository.findAllByDate(LocalDate.now());
    }

    public void deleteAllReportsToday() {
        repository.deleteAllByDate(LocalDate.now());
    }

    public void deleteReportToday(String name, String city, int selection) {
        repository.deleteByNameAndCityAndDateAndSelection(name.toUpperCase(), city.toUpperCase(), LocalDate.now(), selection);
    }
}