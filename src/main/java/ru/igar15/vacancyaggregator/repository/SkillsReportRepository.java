package ru.igar15.vacancyaggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.igar15.vacancyaggregator.model.SkillsReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SkillsReportRepository extends JpaRepository<SkillsReport, Integer> {

    Optional<SkillsReport> findByNameAndCityAndDateAndSelection(String name, String city, LocalDate date, int selection);

    List<SkillsReport> findAllByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM SkillsReport s WHERE s.date=:date")
    void deleteAllByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM SkillsReport s WHERE s.name=:name AND s.city=:city AND s.date=:date AND s.selection=:selection")
    void deleteByNameAndCityAndDateAndSelection(@Param("name") String name, @Param("city") String city,
                                                @Param("date") LocalDate date, @Param("selection") int selection);
}
