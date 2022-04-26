package ru.igar15.skillsaggregator.util;

import ru.igar15.skillsaggregator.model.SkillReport;
import ru.igar15.skillsaggregator.to.SkillReportTo;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SkillReportUtil {
    private static final int ONE_HUNDRED_PERCENT = 100;

    public static SkillReportTo createTo(SkillReport skillReport) {
        Map<String, Integer> skillStatistic =
                createSkillStatistic(skillReport.getSkillCounter(), skillReport.getAnalyzedVacanciesAmount());
        return new SkillReportTo(skillReport.getProfessionName(), skillReport.getCity(), skillReport.getDate(),
                skillReport.getAnalyzedVacanciesAmount(), skillReport.getSelection(), skillStatistic);
    }

    private static Map<String, Integer> createSkillStatistic(Map<String, Integer> skillCounter, int analyzedVacanciesAmount) {
        Function<Entry<String, Integer>, Integer> statisticMapper =
                entry -> entry.getValue() * ONE_HUNDRED_PERCENT / analyzedVacanciesAmount;
        return skillCounter.entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, statisticMapper, (k, v) -> v, LinkedHashMap::new));
    }
}