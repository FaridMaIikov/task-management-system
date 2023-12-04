package com.company.taskmanagementsystem.security.util;

import com.company.taskmanagementsystem.security.SecurityProperties;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@Component
@RequiredArgsConstructor
public class PublicPrivateKeyUtils {

    private final SecurityProperties securityProperties;

    @Value("${security.jwt.private-key}")
    @Getter
    private static PrivateKey privateKey;

    @Value("${security.jwt.public-key}")
    @Getter
    private static PublicKey publicKey;
}
