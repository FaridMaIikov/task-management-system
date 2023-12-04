package com.company.taskmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorResponse handleAlreadyExistsException(AlreadyExistsException exception) {
        return response(exception, HttpStatus.ALREADY_REPORTED.value());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return response(exception, HttpStatus.NOT_FOUND.value());
    }

    private <T extends RuntimeException> ErrorResponse response(T exception, int code) {
        return new ErrorResponse(exception.getMessage(), code, LocalDateTime.now());
    }
}
