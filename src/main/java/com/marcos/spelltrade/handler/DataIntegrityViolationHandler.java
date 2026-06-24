package com.marcos.spelltrade.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.ApiErrorDto;

@RestControllerAdvice
public class DataIntegrityViolationHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorDto> dataIntegrityViolationHandler(
        DataIntegrityViolationException exception) {
            ApiErrorDto error = new ApiErrorDto(exception.getMessage());
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
        }
}
