package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Setter @Getter
public class BusinessAccount extends User{
    private String name;
    private String address;
    private String employeesNumber;
    @Column(length = 2000)
    private String description;

    @Builder
    public BusinessAccount(Long id, String email, String password, Role role, String name, String address, String employeesNumber, String description) {
        super(id, email, password, role);
        this.name = name;
        this.address = address;
        this.employeesNumber = employeesNumber;
        this.description = description;
    }
}
