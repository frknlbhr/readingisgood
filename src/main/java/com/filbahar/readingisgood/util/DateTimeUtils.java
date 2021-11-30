package com.filbahar.readingisgood.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class DateTimeUtils {

    public static LocalDateTime getStartDateTimeOfMonth(int month, int year) {
        validateMonthAndYear(month, year);
        return LocalDate.of(year, month, 1).atStartOfDay();
    }

    public static LocalDateTime getEndDateTimeOfMonth(int month, int year) {
        validateMonthAndYear(month, year);
        LocalDate firstDayOfMonth = getStartDateTimeOfMonth(month, year).toLocalDate();
        return firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59, 999999999);
    }

    private static void validateMonthAndYear(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month value is not valid!");
        }
        if (year < 1970 || year > 2199) {
            throw new IllegalArgumentException("Year value is not valid!");
        }
    }

}
