package com.ym.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public QueueProducer(RabbitTemplate rabbitTemplate, @Qualifier("averagesQueue") Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(String msg) {
        rabbitTemplate.convertAndSend(queue.getName(), msg);
    }
}
