package com.app.service;

import com.app.dto.AuthDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public Map<String, String> login(AuthDTO authDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", jwtService.generateAccessToken((UserDetails) authentication.getPrincipal()));
        if(authDTO.isWithRefreshToken())
            tokens.put("refreshToken", jwtService.generateRefreshToken((UserDetails) authentication.getPrincipal()));
        return tokens;
    }
}
