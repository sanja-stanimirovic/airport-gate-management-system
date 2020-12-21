package com.sstanimirovic.airportgatemanagementsystem.controller;

import com.sstanimirovic.airportgatemanagementsystem.service.exception.GateServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.sstanimirovic.airportgatemanagementsystem.Constants.LABEL_MESSAGE;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.LABEL_TIMESTAMP;
import static com.sstanimirovic.airportgatemanagementsystem.Constants.MESSAGE_SERVER_ERROR;

@ControllerAdvice
public class GateExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GateExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleValidationException(BindException e) {

        logger.error(ExceptionUtils.getStackTrace(e));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(LABEL_TIMESTAMP, LocalDateTime.now());
        String message = Objects.nonNull(e.getBindingResult().getFieldError()) ? e.getBindingResult().getFieldError().getDefaultMessage() : "Field validation error";
        body.put(LABEL_MESSAGE, message);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GateServiceException.class)
    public ResponseEntity<Object> handleGateServiceException(GateServiceException e) {

        logger.error(ExceptionUtils.getStackTrace(e));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(LABEL_TIMESTAMP, LocalDateTime.now());
        body.put(LABEL_MESSAGE, e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAuthenticationException(AccessDeniedException e) {

        logger.error(ExceptionUtils.getStackTrace(e));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(LABEL_TIMESTAMP, LocalDateTime.now());
        body.put(LABEL_MESSAGE, e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultException(Exception e) {

        logger.error(ExceptionUtils.getStackTrace(e));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(LABEL_TIMESTAMP, LocalDateTime.now());
        body.put(LABEL_MESSAGE, MESSAGE_SERVER_ERROR);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
