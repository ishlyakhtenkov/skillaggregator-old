package ru.igar15.skillsaggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.igar15.skillsaggregator.aggregator.SkillAggregator;
import ru.igar15.skillsaggregator.model.Selection;
import ru.igar15.skillsaggregator.model.SkillReport;
import ru.igar15.skillsaggregator.repository.SkillReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class SkillReportService {

    @Autowired
    private SkillReportRepository repository;

    public SkillReport getSkillReportForToday(String professionName, String city, Selection selection) throws IOException {
        Assert.notNull(professionName, "professionName must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(selection, "selection must not be null");
        professionName = professionName.toUpperCase();
        city = city.toUpperCase();
        return repository.findByProfessionNameAndCityAndDateAndSelection(professionName, city,LocalDate.now(), selection)
                .orElse(createSkillReport(professionName, city, selection));
    }

    private SkillReport createSkillReport(String professionName, String city, Selection selection) throws IOException {
        SkillAggregator skillAggregator = new SkillAggregator(professionName, city, selection);
        SkillReport skillReport = skillAggregator.makeSkillReport();
        if (skillReport.getAnalyzedVacanciesAmount() > 0 && !skillReport.getSkillCounter().isEmpty()) {
            saveSkillReport(skillReport);
        }
        return skillReport;
    }

    private SkillReport saveSkillReport(SkillReport skillReport) {
        try {
            return repository.save(skillReport);
        } catch (DataAccessException e) {
            return repository.findByProfessionNameAndCityAndDateAndSelection(skillReport.getProfessionName(),
                    skillReport.getCity(), LocalDate.now(), skillReport.getSelection()).get();
        }
    }

    public List<SkillReport> getAllSkillReportsForToday() {
        return repository.findAllByDate(LocalDate.now());
    }

    public void deleteSkillReportForToday(String professionName, String city, Selection selection) {
        Assert.notNull(professionName, "professionName must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(selection, "selection must not be null");
        repository.deleteByNameAndCityAndDateAndSelection(professionName.toUpperCase(), city.toUpperCase(),
                LocalDate.now(), selection);
    }

    public void deleteAllSkillReportsForToday() {
        repository.deleteAllByDate(LocalDate.now());
    }
}