package com.app.http;

import com.app.dto.ReviewResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "REVIEWS-MICROSERVICE"
)
public interface ReviewClient {

    @GetMapping("/reviews/service/{serviceId}")
    List<ReviewResponseDTO> getServiceReviews(@PathVariable Long serviceId);
}
