package ru.igar15.vacancyaggregator.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import ru.igar15.vacancyaggregator.service.SkillsReportService;

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
                .map(report -> report.getName() + ", " + report.getCity() + ", " + report.getSelection())
                .collect(Collectors.toList());
    }

    @ManagedOperation(description = "Delete all skills reports for today")
    public void deleteAllReportsToday() {
        service.deleteAllReportsToday();
    }

    @ManagedOperation(description = "Delete skill report for today")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "name", description = "Vacancy name"),
            @ManagedOperationParameter(name = "city", description = "Vacancy city"),
            @ManagedOperationParameter(name = "selection", description = "Selection"),
    })
    public void deleteReportToday(String name, String city, int selection) {
        service.deleteReportToday(name, city, selection);
    }
}