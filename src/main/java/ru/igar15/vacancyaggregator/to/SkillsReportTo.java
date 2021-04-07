package ru.igar15.vacancyaggregator.to;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class SkillsReportTo {
    private Integer id;
    private String name;
    private String city;
    private LocalDate date;
    private int selection;
    private int vacanciesAmount;
    private Map<String, String> keySkills;

    public SkillsReportTo(Integer id, String name, String city, LocalDate date, int selection, int vacanciesAmount, Map<String, String> keySkills) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.date = date;
        this.selection = selection;
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

    public int getSelection() {
        return selection;
    }

    public int getVacanciesAmount() {
        return vacanciesAmount;
    }

    public Map<String, String> getKeySkills() {
        return keySkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsReportTo that = (SkillsReportTo) o;
        return selection == that.selection &&
                vacanciesAmount == that.vacanciesAmount &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(city, that.city) &&
                Objects.equals(date, that.date) &&
                Objects.equals(keySkills, that.keySkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, date, selection, vacanciesAmount, keySkills);
    }
}
