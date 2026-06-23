package com.marcos.spelltrade.dto;

import java.util.Map;

public record ValidationErrorDto(
    Map<String, String> fields
) {
    
}
