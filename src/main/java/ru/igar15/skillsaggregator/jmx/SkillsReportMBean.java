package ru.igar15.skillsaggregator.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import ru.igar15.skillsaggregator.model.Selection;
import ru.igar15.skillsaggregator.service.SkillsReportService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ManagedResource(description = "Skills Report Manage Bean")
public class SkillsReportMBean {

    @Autowired
    private SkillsReportService service;

    @ManagedOperation(description = "Get all skills reports for today")
    public List<String> getAllReportsToday() {
        return service.getAllReportsToday().stream()
                .map(report -> report.getProfessionName() + ", " + report.getCity() + ", " + report.getSelection())
                .collect(Collectors.toList());
    }

    @ManagedOperation(description = "Delete all skills reports for today")
    public void deleteAllReportsToday() {
        service.deleteAllReportsToday();
    }

    @ManagedOperation(description = "Delete skills report for today")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "professionName", description = "Profession name"),
            @ManagedOperationParameter(name = "city", description = "City"),
            @ManagedOperationParameter(name = "selection", description = "Selection (FIRST_100_VACANCIES, " +
                                                                         "FIRST_500_VACANCIES or FIRST_2000_VACANCIES)")
    })
    public void deleteReportToday(String name, String city, String selection) {
        service.deleteReportToday(name, city, Selection.valueOf(selection));
    }
}