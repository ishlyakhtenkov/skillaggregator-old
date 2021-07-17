package ru.igar15.vacancyaggregator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "skills_reports")
public class SkillsReport extends Report {

    @Column(name = "vacancies_amount")
    private int vacanciesAmount;

    @Column(name = "skills")
    private String skills;

    public SkillsReport() {
    }

    public SkillsReport(String name, String city, int selection, int vacanciesAmount, String skills) {
        this(null, name, city, LocalDate.now(), selection, vacanciesAmount, skills);
    }

    public SkillsReport(Integer id, String name, String city, LocalDate date, int selection, int vacanciesAmount, String skills) {
        super(id, name, city, date, selection);
        this.vacanciesAmount = vacanciesAmount;
        this.skills = skills;
    }

    public int getVacanciesAmount() {
        return vacanciesAmount;
    }

    public void setVacanciesAmount(int vacanciesAmount) {
        this.vacanciesAmount = vacanciesAmount;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String keySkills) {
        this.skills = keySkills;
    }
}