package ru.igar15.skillsaggregator.to;

import ru.igar15.skillsaggregator.model.Selection;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class SkillsReportTo {
    private Integer id;
    private String professionName;
    private String city;
    private LocalDate date;
    private int analyzedVacanciesAmount;
    private Map<String, String> skills;
    private Selection selection;

    public SkillsReportTo(Integer id, String professionName, String city, LocalDate date, int analyzedVacanciesAmount, Map<String, String> skills, Selection selection) {
        this.id = id;
        this.professionName = professionName;
        this.city = city;
        this.date = date;
        this.analyzedVacanciesAmount = analyzedVacanciesAmount;
        this.skills = skills;
        this.selection = selection;
    }

    public Integer getId() {
        return id;
    }

    public String getProfessionName() {
        return professionName;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    public Selection getSelection() {
        return selection;
    }

    public int getAnalyzedVacanciesAmount() {
        return analyzedVacanciesAmount;
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsReportTo that = (SkillsReportTo) o;
        return selection == that.selection &&
                analyzedVacanciesAmount == that.analyzedVacanciesAmount &&
                Objects.equals(id, that.id) &&
                Objects.equals(professionName, that.professionName) &&
                Objects.equals(city, that.city) &&
                Objects.equals(date, that.date) &&
                Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professionName, city, date, selection, analyzedVacanciesAmount, skills);
    }
}