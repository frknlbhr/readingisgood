package com.filbahar.readingisgood.model;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public enum OrderStatus {

    PREPARING("Preparing"),
    TRANSFERING("Transfering"),
    DELIVERED("Delivered"),
    RETURNED("Returned");

    private String label;

    private OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
