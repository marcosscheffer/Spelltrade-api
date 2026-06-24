package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.marcos.spelltrade.dto.ApiErrorDto;

@RestControllerAdvice
public class TokenExpiredHandler {
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorDto tokenExpiredHandler(TokenExpiredException exception) {
        ApiErrorDto error = new ApiErrorDto(exception.getMessage());
        return error;
    }
}
