package com.filbahar.readingisgood.unit.util;

import com.filbahar.readingisgood.util.DateTimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author filbahar
 * @created 29.11.2021
 */

public class DateTimeUtilsTest {

    @Test
    void test_getStartDateTimeOfMonth_success() {
        LocalDateTime expected = LocalDateTime.of(1990, 2, 1, 0, 0, 0);
        LocalDateTime result = DateTimeUtils.getStartDateTimeOfMonth(2, 1990);
        Assertions.assertEquals(expected.getYear(), result.getYear());
        Assertions.assertEquals(expected.getMonth(), result.getMonth());
        Assertions.assertEquals(expected.getDayOfMonth(), result.getDayOfMonth());
        Assertions.assertEquals(expected.getHour(), result.getHour());
        Assertions.assertEquals(expected.getMinute(), result.getMinute());
        Assertions.assertEquals(expected.getSecond(), result.getSecond());
    }

    @Test
    void test_getEndDateTimeOfMonth_success() {
        LocalDateTime expected = LocalDateTime.of(2033, 12, 31, 23, 59, 59, 999999999);
        LocalDateTime result = DateTimeUtils.getEndDateTimeOfMonth(12, 2033);
        Assertions.assertEquals(expected.getYear(), result.getYear());
        Assertions.assertEquals(expected.getMonth(), result.getMonth());
        Assertions.assertEquals(expected.getDayOfMonth(), result.getDayOfMonth());
        Assertions.assertEquals(expected.getHour(), result.getHour());
        Assertions.assertEquals(expected.getMinute(), result.getMinute());
        Assertions.assertEquals(expected.getSecond(), result.getSecond());
        Assertions.assertEquals(expected.getNano(), result.getNano());
    }

    @Test
    void test_getStartDateTimeOfMonth_invalidMonth() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DateTimeUtils.getStartDateTimeOfMonth(14, 2020));
    }

    @Test
    void test_getEndDateTimeOfMonth_invalidYear() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DateTimeUtils.getEndDateTimeOfMonth(6, 1900));
    }

}
