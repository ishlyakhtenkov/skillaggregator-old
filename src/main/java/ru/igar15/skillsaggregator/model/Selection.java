package ru.igar15.skillsaggregator.model;

public enum Selection {
    FIRST_100_VACANCIES(100),
    FIRST_500_VACANCIES(500),
    FIRST_2000_VACANCIES(2000);

    private int vacanciesAmount;

    Selection(int vacanciesAmount) {
        this.vacanciesAmount = vacanciesAmount;
    }
}
