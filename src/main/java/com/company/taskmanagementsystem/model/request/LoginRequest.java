package com.company.taskmanagementsystem.model.request;

public record LoginRequest(
        String email,
        String password,
        Boolean rememberMe
) {
}
