package com.marcos.spelltrade.dto.common;

import java.util.Map;

public record ValidationErrorDto(
    Map<String, String> fields
) {
    
}
