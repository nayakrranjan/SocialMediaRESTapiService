package com.restful.social.socialmediarestapi.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handelUserNotFoundExceptions (Exception e, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), HttpStatus.NOT_FOUND.name(),
                        e.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers,
                HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.name(),
                        Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
