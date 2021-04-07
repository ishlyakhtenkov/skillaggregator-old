package ru.igar15.vacancyaggregator.util;

import java.util.*;

public class MapSortUtil {
    private MapSortUtil() {
        throw new UnsupportedOperationException();
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> map, Comparator<V> sortOrder) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(sortOrder));
        Map<K, V> sortedMap = new LinkedHashMap<>();
        list.forEach(kvEntry -> sortedMap.put(kvEntry.getKey(), kvEntry.getValue()));
        return sortedMap;
    }
}