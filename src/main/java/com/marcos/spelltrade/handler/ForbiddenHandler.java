package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.common.ApiErrorDto;
import com.marcos.spelltrade.exception.ForbiddenException;

@RestControllerAdvice
public class ForbiddenHandler {
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiErrorDto> forbiddenHandler(ForbiddenException exception) {
        ApiErrorDto error = new ApiErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
