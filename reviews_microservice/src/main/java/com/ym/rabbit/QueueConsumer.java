package com.ym.rabbit;

import com.ym.model.Review;
import com.ym.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QueueConsumer {
    private ReviewRepository reviewRepository;
    private QueueProducer queueProducer;

    @RabbitListener(queues = {"reviews"})
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
        String message = review.getServiceId() + ";" + reviews.size() + ";" + average;
        queueProducer.send(message);
    }
}
