package com.ym.web;

import com.ym.dto.ReviewResponseDTO;
import com.ym.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping("/service/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponseDTO> getReviewsByServiceId(@PathVariable Long serviceId){
        return reviewService.getReviewsByServiceId(serviceId);
    }

    @GetMapping("/accounts/{personnelAccountId}/service/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean personnelAccountDidReview(
            @PathVariable Long personnelAccountId,
            @PathVariable Long serviceId
    ){
        return reviewService.personnelAccountDidReview(personnelAccountId, serviceId);
    }
}
