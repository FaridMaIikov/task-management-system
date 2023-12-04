package com.company.taskmanagementsystem.security;

import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.security.util.PublicPrivateKeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AccessTokenManager implements TokenGenerator<User>,TokenReader<Claims>{

private final SecurityProperties securityProperties;

    @Override
    public String generate(User user) {

        Claims claims = Jwts.claims();
        claims.put("email",user.getEmail());

        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + securityProperties.getJwt().getAccessTokenValidityTime());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(expiredTime)
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


    public String extractUsername(String token) {
        return (read(token).get("email",String.class));
    }

    public boolean isTokenExpired(String token){
        return read(token).getExpiration().before(new Date());
    }



    public boolean isTokenValid(String jwt, UserDetails user) {
        final String email = extractUsername(jwt);
        return (email.equals(user.getUsername())) && !isTokenExpired(jwt);
    }
}
