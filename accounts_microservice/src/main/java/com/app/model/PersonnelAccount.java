package com.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @Setter @Getter
public class PersonnelAccount extends User{

    private String firstName;
    private String lastName;

    @Builder
    public PersonnelAccount(Long id, String email, String password, Role role, String firstName, String lastName) {
        super(id, email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
