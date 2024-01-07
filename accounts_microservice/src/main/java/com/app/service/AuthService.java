package com.app.service;

import com.app.dto.AuthDTO;
import com.app.model.User;
import com.app.repository.UserRepository;
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
    private UserRepository userRepository;

    public Map<String, String> login(AuthDTO authDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        User user = userRepository.findByEmail(((UserDetails) authentication.getPrincipal()).getUsername()).get();
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", jwtService.generateAccessToken((UserDetails) authentication.getPrincipal(), user.getId()));
        if(authDTO.isWithRefreshToken())
            tokens.put("refreshToken", jwtService.generateRefreshToken((UserDetails) authentication.getPrincipal()));
        return tokens;
    }
}