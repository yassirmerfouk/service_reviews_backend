package com.app.repository;

import com.app.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    boolean existsByCategoryId(Long categoryId);
    List<Service> findByCategoryId(Long categoryId);
}
