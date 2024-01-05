package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BusinessAccountResponseDTO {
    protected Long id;
    protected String email;
    private String name;
    private String address;
    private String employeesNumber;
    private String description;
}
