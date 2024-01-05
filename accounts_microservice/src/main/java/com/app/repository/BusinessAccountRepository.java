package com.app.repository;

import com.app.model.BusinessAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessAccountRepository extends JpaRepository<BusinessAccount,Long> {
}
