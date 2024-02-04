package com.app.service;

import com.app.config.SecurityUser;
import com.app.model.User;
import com.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtService {

    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private UserDetailsService userDetailsService;

    public String generateAccessToken(SecurityUser securityUser) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(securityUser.getUsername())
                .issuedAt(instant)
                .expiresAt(instant.plus(60, ChronoUnit.MINUTES))
                .claim("userId", securityUser.getUser().getId())
                .claim("scope", securityUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String generateRefreshToken(SecurityUser securityUser) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(securityUser.getUsername())
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.DAYS))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String refreshToken(String refreshToken) throws JwtException {
        Jwt jwt = jwtDecoder.decode(refreshToken);
        String subject = jwt.getSubject();
        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(subject);
        return generateAccessToken(securityUser);
    }
}
