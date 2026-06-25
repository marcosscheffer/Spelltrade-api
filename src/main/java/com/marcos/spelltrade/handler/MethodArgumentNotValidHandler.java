package com.marcos.spelltrade.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.marcos.spelltrade.dto.common.ValidationErrorDto;

@RestControllerAdvice
public class MethodArgumentNotValidHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleValidation(
        MethodArgumentNotValidException exception) {
            Map<String, String> errors = new LinkedHashMap<>();

            exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                    error.getField(), 
                    error.getDefaultMessage()
                ));
            
            ValidationErrorDto error = new ValidationErrorDto(errors);
            
            return ResponseEntity.badRequest().body(error);
    }
}
