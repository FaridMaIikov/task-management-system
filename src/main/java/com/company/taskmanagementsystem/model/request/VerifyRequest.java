package com.company.taskmanagementsystem.model.request;

import lombok.Data;

@Data
public class VerifyRequest {
    String email;
    String code;
}
