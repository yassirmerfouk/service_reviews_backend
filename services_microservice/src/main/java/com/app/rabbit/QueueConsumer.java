package com.app.rabbit;
import com.app.model.Service;
import com.app.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueueConsumer {

    private ServiceRepository serviceRepository;

    @RabbitListener(queues = {"averages"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
        String[] composers = fileBody.split(";",3);
        Service service = serviceRepository.findById(Long.parseLong(composers[0])).get();
        service.setReviewsNumbers(Integer.parseInt(composers[1]));
        service.setReviewsAverage(Double.parseDouble(composers[2]));
        serviceRepository.save(service);
    }
}
