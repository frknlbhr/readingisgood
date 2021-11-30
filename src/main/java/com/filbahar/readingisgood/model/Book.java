package com.filbahar.readingisgood.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Document
public class Book extends BaseEntity {

    @NotBlank
    private String name;
    private String author;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price = BigDecimal.ZERO;
    private int numberInStock;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }
}
