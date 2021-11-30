package com.filbahar.readingisgood.exception;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
