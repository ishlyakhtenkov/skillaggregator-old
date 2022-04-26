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
import java.util.Optional;

@Service
public class SkillReportService {

    @Autowired
    private SkillReportRepository repository;

    public SkillReport getSkillReportForToday(String professionName, String city, Selection selection) throws IOException {
        checkParamsOnNull(professionName, city, selection);
        professionName = professionName.toUpperCase();
        city = city.toUpperCase();
        Optional<SkillReport> optionalSkillReport =
                repository.findByProfessionNameAndCityAndDateAndSelection(professionName, city, LocalDate.now(), selection);
        if (optionalSkillReport.isPresent()) {
            return optionalSkillReport.get();
        } else {
            return createSkillReport(professionName, city, selection);
        }
    }

    private void checkParamsOnNull(String professionName, String city, Selection selection) {
        Assert.notNull(professionName, "professionName must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(selection, "selection must not be null");
    }

    private SkillReport createSkillReport(String professionName, String city, Selection selection) throws IOException {
        SkillAggregator skillAggregator = new SkillAggregator(professionName, city, selection);
        SkillReport skillReport = skillAggregator.makeSkillReport();
        checkSkillReport(skillReport);
        return saveSkillReport(skillReport);
    }

    private void checkSkillReport(SkillReport skillReport) {
        if (skillReport.getAnalyzedVacanciesAmount() == 0) {
            throw new EmptySkillReportException("Skill report has 0 analyzed vacancies");
        }
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
        checkParamsOnNull(professionName, city, selection);
        repository.deleteByNameAndCityAndDateAndSelection(professionName.toUpperCase(), city.toUpperCase(),
                LocalDate.now(), selection);
    }

    public void deleteAllSkillReportsForToday() {
        repository.deleteAllByDate(LocalDate.now());
    }
}