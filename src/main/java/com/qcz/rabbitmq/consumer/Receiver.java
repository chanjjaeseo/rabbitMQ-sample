package com.qcz.rabbitmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component

public class Receiver {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "queue01")
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        logger.info("Message is consuming: {}", message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, true);
    }

    @RabbitListener(queues = "queue01")
    @RabbitHandler
    public void onMessage(@Payload String message, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        logger.info("Message is consuming: {}", message);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, true);
    }


}
