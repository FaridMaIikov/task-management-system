package com.company.taskmanagementsystem.model.request;

import lombok.Data;

@Data
public class JwtRefreshRequest {
    private String newToken;
    private String refreshToken;
}
