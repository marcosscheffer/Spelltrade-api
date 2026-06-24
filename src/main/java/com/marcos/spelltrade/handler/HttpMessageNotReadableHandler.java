package com.marcos.spelltrade.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.ApiErrorDto;

@RestControllerAdvice
public class HttpMessageNotReadableHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDto> messageNotReadableHandler(
        HttpMessageNotReadableException exception) {
            ApiErrorDto error = new ApiErrorDto("Required request body is invalid");

            return ResponseEntity.badRequest().body(error);
    }
}
