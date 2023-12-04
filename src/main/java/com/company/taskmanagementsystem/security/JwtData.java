package com.company.taskmanagementsystem.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtData {

    @Value("${spring.jwt.access-token-validity-time}")
    Integer accessTokenValidityTime;

    @Value("${spring.jwt.refresh-token-validity-time}")
    Integer refreshTokenValidityTime;

    private final SecurityProperties securityProperties;

    public Long getRefreshTokenValidityTime(boolean rememberMe) {
        return refreshTokenValidityTime * (rememberMe ? 30L : 1L);
    }
}
