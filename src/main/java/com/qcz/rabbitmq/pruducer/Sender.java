package com.qcz.rabbitmq.pruducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class Sender {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 确认消息是否到达
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean isAcked, String cause) {
            logger.info("CorrelationId: {}; isAcked: {}; cause: {}", correlationData.getId(), isAcked, cause);
        }
    };

    // 处理不可路由的消息 (需设置mandatory=true,否则不回回调,消息就丢了)
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message,
                                    int replyCode, String replyText, String exchange, String routingKey) {
            MessageProperties messageProperties = message.getMessageProperties();
            String correlationId = messageProperties.getCorrelationId();
            logger.info("CorrelationId: {}; exchange: {}; routingKey: {} \\n replyCode: {}; replyText: {}",
                    correlationId, exchange, routingKey, replyCode, replyText);
        }
    };

    public void send(Object message, Map<String, Object> properties){
        MessageHeaders messageHeader = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, messageHeader);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("exchange01", "simple.routing", msg, correlationData);
    }

}
