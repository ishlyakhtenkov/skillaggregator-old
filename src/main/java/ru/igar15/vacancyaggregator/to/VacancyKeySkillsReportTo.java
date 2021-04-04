package ru.igar15.vacancyaggregator.to;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VacancyKeySkillsReportTo {
    private Integer id;
    private String name;
    private String city;
    private LocalDate date;
    private int vacanciesAmount;
    private Map<String, String> keySkills;

    public VacancyKeySkillsReportTo(Integer id, String name, String city, LocalDate date, int vacanciesAmount, Map<String, String> keySkills) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.date = date;
        this.vacanciesAmount = vacanciesAmount;
        this.keySkills = keySkills;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getVacanciesAmount() {
        return vacanciesAmount;
    }

    public Map<String, String> getKeySkills() {
        return keySkills;
    }
}
