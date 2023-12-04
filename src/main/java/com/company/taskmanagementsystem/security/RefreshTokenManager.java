package com.company.taskmanagementsystem.security;

import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.model.response.RefreshTokenDto;
import com.company.taskmanagementsystem.security.util.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto>,TokenReader<Claims>{

    private final SecurityProperties securityProperties;

    @Override
    public String generate(RefreshTokenDto refreshTokenDto) {
        final User user = refreshTokenDto.getUser();

        Claims claims = Jwts.claims();
        claims.put("email",user.getEmail());
        claims.put("type","REFRESH_TOKEN");

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperties.getJwt().getRefreshTokenValidityTime(refreshTokenDto.isRememberMe()));

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtils.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
