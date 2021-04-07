package ru.igar15.vacancyaggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.igar15.vacancyaggregator.model.SkillsReport;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SkillsReportRepository extends JpaRepository<SkillsReport, Integer> {

    Optional<SkillsReport> findByNameAndCityAndDateAndSelection(String name, String city, LocalDate date, int selection);
}
