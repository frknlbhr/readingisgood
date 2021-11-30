package com.filbahar.readingisgood.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class OrderStatisticsDto implements Serializable {

    public static final long serialVersionUID = 1L;

    private int month;
    private int year;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;

    public OrderStatisticsDto() {
        //default
    }

    public OrderStatisticsDto(int month, int year, int totalOrderCount, int totalBookCount, BigDecimal totalPurchasedAmount) {
        this.month = month;
        this.year = year;
        this.totalOrderCount = totalOrderCount;
        this.totalBookCount = totalBookCount;
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public int getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(int totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public BigDecimal getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }
}
