package com.marcos.spelltrade.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.common.ApiErrorDto;
import com.marcos.spelltrade.exception.BusinessException;

@RestControllerAdvice
public class BusinessHandler {
    @ExceptionHandler(BusinessException.class) 
    public ResponseEntity<ApiErrorDto> businessHandler(BusinessException exception) {
        ApiErrorDto error = new ApiErrorDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
