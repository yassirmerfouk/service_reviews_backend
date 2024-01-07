package com.app.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtService {

    private JwtEncoder jwtEncoder;

    public String generateAccessToken(UserDetails userDetails, Long id){
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuer("SERVICE-REVIEWS")
                .issuedAt(instant)
                .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                .claim("userId", id)
                .claim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String generateRefreshToken(UserDetails userDetails){
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuer("SERVICE-REVIEWS")
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.DAYS))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
