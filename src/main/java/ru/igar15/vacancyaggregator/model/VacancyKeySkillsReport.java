package ru.igar15.vacancyaggregator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "vacancy_key_skills_reports")
public class VacancyKeySkillsReport extends VacancyReport {

    @Column(name = "vacancies_amount")
    private int vacanciesAmount;

    @Column(name = "key_skills")
    private String keySkills;

    public VacancyKeySkillsReport() {
    }

    public VacancyKeySkillsReport(String name, String city, int selection, int vacanciesAmount, String keySkills) {
        this(null, name, city, LocalDate.now(), selection, vacanciesAmount, keySkills);
    }

    public VacancyKeySkillsReport(Integer id, String name, String city, LocalDate date, int selection, int vacanciesAmount, String keySkills) {
        super(id, name, city, date, selection);
        this.vacanciesAmount = vacanciesAmount;
        this.keySkills = keySkills;
    }

    public int getVacanciesAmount() {
        return vacanciesAmount;
    }

    public void setVacanciesAmount(int vacanciesAmount) {
        this.vacanciesAmount = vacanciesAmount;
    }

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }
}