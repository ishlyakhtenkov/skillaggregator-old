package ru.igar15.skillsaggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.igar15.skillsaggregator.aggregator.Aggregator;
import ru.igar15.skillsaggregator.model.Selection;
import ru.igar15.skillsaggregator.model.SkillsReport;
import ru.igar15.skillsaggregator.repository.SkillsReportRepository;

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

    public Optional<SkillsReport> getReportToday(String professionName, String city, Selection selection) throws IOException {
        Assert.notNull(professionName, "professionName must not be null");
        Assert.notNull(city, "city must not be null");
        professionName = professionName.toUpperCase();
        city = city.toUpperCase();
        Optional<SkillsReport> skillsReport = repository.findByProfessionNameAndCityAndDateAndSelection(professionName,
                city, LocalDate.now(), selection);
        if (skillsReport.isEmpty()) {
            skillsReport = vacancyKeySkillsAggregator.getReport(professionName, city, selection);
            if (skillsReport.isPresent()) {
                try {
                    repository.save(skillsReport.get());
                } catch (DataAccessException e) {
                    return repository.findByProfessionNameAndCityAndDateAndSelection(professionName, city, LocalDate.now(),
                            selection);
                }
            }
        }
        return skillsReport;
    }

    public List<SkillsReport> getAllReportsToday() {
        return repository.findAllByDate(LocalDate.now());
    }

    public void deleteAllReportsToday() {
        repository.deleteAllByDate(LocalDate.now());
    }

    public void deleteReportToday(String professionName, String city, Selection selection) {
        repository.deleteByNameAndCityAndDateAndSelection(professionName.toUpperCase(), city.toUpperCase(),
                LocalDate.now(), selection);
    }
}