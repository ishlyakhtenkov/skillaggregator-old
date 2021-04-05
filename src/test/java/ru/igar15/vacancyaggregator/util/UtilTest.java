package ru.igar15.vacancyaggregator.util;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    @Test
    void sortMapByValue() {
        Map<String, Integer> mapTestData =
                Map.of("key1", 30, "key2", 20, "key3", 80, "key4", 50, "key5", 90);

        Map<String, Integer> expectedMap =new LinkedHashMap<>();
        expectedMap.put("key5", 90);
        expectedMap.put("key3", 80);
        expectedMap.put("key4", 50);
        expectedMap.put("key1", 30);
        expectedMap.put("key2", 20);
        String expectedMapKeySetOrder = expectedMap.keySet().stream().collect(Collectors.joining(","));

        Map<String, Integer> actualMap = Util.sortMapByValue(mapTestData, Comparator.reverseOrder());
        String actualMapKeySetOrder = actualMap.keySet().stream().collect(Collectors.joining(","));
        assertEquals(expectedMapKeySetOrder, actualMapKeySetOrder);
    }
}