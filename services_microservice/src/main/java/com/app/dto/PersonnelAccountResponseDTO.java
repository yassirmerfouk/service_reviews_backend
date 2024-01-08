package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PersonnelAccountResponseDTO {
    protected Long id;
    protected String email;
    private String firstName;
    private String lastName;
}
