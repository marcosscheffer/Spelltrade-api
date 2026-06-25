package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.common.ApiErrorDto;

@RestControllerAdvice
public class HttpRequestMethodNotSupportedHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) 
    public ResponseEntity<ApiErrorDto> httpRequestMethodNotSupportHandler(
        HttpRequestMethodNotSupportedException exception) {
            ApiErrorDto error = new ApiErrorDto(exception.getMessage());
            return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(error);
        }
}
