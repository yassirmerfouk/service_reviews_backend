package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BusinessAccountRequestDTO {
    private String email;
    private String password;
    private String name;
    private String address;
    private String employeesNumber;
    private String description;
}
