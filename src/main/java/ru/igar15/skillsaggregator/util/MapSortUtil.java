package ru.igar15.skillsaggregator.util;

import java.util.*;

public class MapSortUtil {
    private MapSortUtil() {
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> map, Comparator<V> sortOrder) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue(sortOrder));
        Map<K, V> sortedMap = new LinkedHashMap<>();
        entries.forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
    }
}