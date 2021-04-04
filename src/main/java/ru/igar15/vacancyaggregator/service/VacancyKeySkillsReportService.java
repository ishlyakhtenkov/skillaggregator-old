package ru.igar15.vacancyaggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.igar15.vacancyaggregator.aggregators.VacancyAggregator;
import ru.igar15.vacancyaggregator.model.VacancyKeySkillsReport;
import ru.igar15.vacancyaggregator.repository.VacancyKeySkillsReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class VacancyKeySkillsReportService {

    @Autowired
    private VacancyKeySkillsReportRepository repository;

    @Autowired
    private VacancyAggregator vacancyKeySkillsAggregator;

    public VacancyKeySkillsReport getReportToday(String name, String city) throws IOException {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(city, "city must not be null");
        Optional<VacancyKeySkillsReport> report = repository.findByNameAndCityAndDate(name.toUpperCase(), city.toUpperCase(), LocalDate.now());
        if (report.isPresent()) {
            return report.get();
        } else {
            VacancyKeySkillsReport vacancyReport = (VacancyKeySkillsReport) vacancyKeySkillsAggregator.getAggregationResult(name.toUpperCase(), city.toUpperCase());
            if (vacancyReport.getVacanciesAmount() > 0) {
                repository.save(vacancyReport);
            }
            return vacancyReport;
        }
    }
}