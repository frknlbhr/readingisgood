package com.filbahar.readingisgood.dto;

import java.math.BigDecimal;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class BookDto {

    private String id;
    private String name;
    private String author;
    private Integer numberInStock;
    private BigDecimal price;

    public BookDto() {
        //default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
