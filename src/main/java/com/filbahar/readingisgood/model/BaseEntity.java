package com.filbahar.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class BaseEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Version
    @JsonIgnore
    private Long version;
    @CreatedBy
    @JsonIgnore
    private String createdBy;
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;
    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
