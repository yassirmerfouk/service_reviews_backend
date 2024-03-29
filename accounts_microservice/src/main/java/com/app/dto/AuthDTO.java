package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AuthDTO {

    private String email;
    private String password;
    private boolean withRefreshToken;
}
