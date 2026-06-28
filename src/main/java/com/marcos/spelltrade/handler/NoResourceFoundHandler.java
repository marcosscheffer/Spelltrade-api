package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.marcos.spelltrade.dto.common.ApiErrorDto;

@RestControllerAdvice
public class NoResourceFoundHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorDto> noResourceFoundHandler(NoResourceFoundException exception) {
        ApiErrorDto error = new ApiErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
