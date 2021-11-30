package com.filbahar.readingisgood.exception;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class ExceptionResponse {

    private String errorMessage;
    private String path;
    private String errorCode;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String errorMessage, String path, String errorCode) {
        this.errorMessage = errorMessage;
        this.path = path;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
