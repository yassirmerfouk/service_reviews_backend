package com.ym.repository;

import com.ym.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByServiceId(Long serviceId);
    boolean existsByServiceIdAndPersonnelAccountId(Long serviceId, Long personnelAccountId);
}
