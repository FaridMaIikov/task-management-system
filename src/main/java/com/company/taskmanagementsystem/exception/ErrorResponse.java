package com.company.taskmanagementsystem.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        int code,
        LocalDateTime localDateTime
) {
}
