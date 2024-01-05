package com.app.repository;

import com.app.model.PersonnelAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelAccountRepository extends JpaRepository<PersonnelAccount,Long> {
}
