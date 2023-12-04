package com.company.taskmanagementsystem.model.response;


import com.company.taskmanagementsystem.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {

    boolean rememberMe;
    User user;
}
