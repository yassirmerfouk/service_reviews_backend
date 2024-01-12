package com.ym.service;

import com.ym.dto.ReviewResponseDTO;
import com.ym.mapper.ReviewMapper;
import com.ym.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    public List<ReviewResponseDTO> getReviewsByServiceId(Long serviceId){
        return reviewRepository.findByServiceId(serviceId).stream()
                .map(review -> reviewMapper.toReviewResponseDTO(review))
                .collect(Collectors.toList());
    }

    public boolean personnelAccountDidReview(Long personnelAccountId, Long serviceId){
        return reviewRepository.existsByServiceIdAndPersonnelAccountId(serviceId, personnelAccountId);
    }
}
