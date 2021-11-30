package com.filbahar.readingisgood.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_DATE_FORMAT = "yyyyMMdd'T'HHmmss";

    @Value("${spring.application.name}")
    private String applicationName;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleEntityNotFound(final EntityNotFoundException exception, final HttpServletRequest request) {
        return new ExceptionResponse(exception.getMessage(), request.getServletPath(), generateErrorCode());
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ExceptionResponse handleOptimisticLockingFailure(final OptimisticLockingFailureException exception, final HttpServletRequest request) {
        return new ExceptionResponse("Eş zamanlı güncelleme hatası! Lütfen tekrar deneyiniz.", request.getServletPath(), generateErrorCode());
    }

    @ExceptionHandler(OperationNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleOperationNotSupported(final OperationNotSupportedException exception, final HttpServletRequest request) {
        return new ExceptionResponse("Yapmaya çalıştığınız güncelleme işlemi desteklenmemektedir!", request.getServletPath(), generateErrorCode());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleException(final Exception exception, final HttpServletRequest request) {
        return new ExceptionResponse("Sunucu Hatası", request.getServletPath(), generateErrorCode());
    }

    private String generateErrorCode() {
        return applicationName + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(ERROR_DATE_FORMAT));
    }

}
