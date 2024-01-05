package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PersonnelAccountRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
