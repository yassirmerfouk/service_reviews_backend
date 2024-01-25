package com.ym.rabbit;

import com.ym.dto.UpdateAverageRequestDTO;
import com.ym.http.ServiceClient;
import com.ym.model.Review;
import com.ym.repository.ReviewRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueueConsumer {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ServiceClient serviceClient;

    @RabbitListener(queues = {"${queue.reviews}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
        String[] composers = fileBody.split(";",4);
        Review review = Review.builder()
                .serviceId(Long.parseLong(composers[0]))
                .personnelAccountId(Long.parseLong(composers[1]))
                .grade(Integer.parseInt(composers[2]))
                .comment(composers[3])
                .build();
        reviewRepository.save(review);
        List<Review> reviews = reviewRepository.findByServiceId(Long.parseLong(composers[0]));
        double average = reviews.stream().mapToDouble(r -> r.getGrade()).sum() / reviews.size();
        System.out.println("size : " +reviews.size());
        System.out.println("average : " +average);
        UpdateAverageRequestDTO updateAverageRequestDTO = new UpdateAverageRequestDTO();
        updateAverageRequestDTO.setAverage(average);
        updateAverageRequestDTO.setTotal(reviews.size());
        serviceClient.sendAverageAndTotal(Long.parseLong(composers[0]),updateAverageRequestDTO);
    }
}
