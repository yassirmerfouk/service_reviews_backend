package com.ym.mapper;

import com.ym.dto.ReviewResponseDTO;
import com.ym.model.Review;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponseDTO toReviewResponseDTO(Review review){
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
        BeanUtils.copyProperties(review, reviewResponseDTO);
        return reviewResponseDTO;
    }
}
