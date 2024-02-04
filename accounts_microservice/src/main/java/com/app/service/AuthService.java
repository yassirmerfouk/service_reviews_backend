package com.app.service;

import com.app.config.SecurityUser;
import com.app.dto.AuthDTO;
import com.app.model.User;
import com.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserRepository userRepository;

    public Map<String, String> login(AuthDTO authDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", jwtService.generateAccessToken(securityUser));
        if(authDTO.isWithRefreshToken())
            tokens.put("refreshToken", jwtService.generateRefreshToken(securityUser));
        return tokens;
    }

    public Map<String, String> refreshToken(String refreshToken){
        try{
            return Map.of(
                    "accessToken", jwtService.refreshToken(refreshToken),
                    "refreshToken", refreshToken
            );
        }catch(JwtException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
