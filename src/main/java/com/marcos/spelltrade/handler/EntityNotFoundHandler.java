package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.common.ApiErrorDto;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class EntityNotFoundHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorDto> entityNotFoundHandler(
        EntityNotFoundException exception) {
            ApiErrorDto error = new ApiErrorDto(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
