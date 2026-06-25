package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.common.ApiErrorDto;

@RestControllerAdvice
public class BadCredentialsHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorDto> badCredentialsHandler(
        BadCredentialsException exception) {
            ApiErrorDto error = new ApiErrorDto("Username does not exist or password is invalid.");

            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
}
