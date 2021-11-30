package com.filbahar.readingisgood.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Document
public class Customer extends BaseEntity {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @Indexed(unique = true, name = "email_unique_index")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
