package com.qcz.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcz.rabbitmq.pojo.Package;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageConvertTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void messageConvertOfMapTest() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(generatePackage());

        sendMessage(content.getBytes());
    }

    @Test
    public void messageConvertOfObjectTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(generatePackage());

        sendMessage(content.getBytes());
    }

    private void sendMessage(byte[] messsageBody) {
        MessageProperties messageProperties = new MessageProperties();

        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);

        messageProperties.setMessageId("message_" + UUID.randomUUID().toString());

        messageProperties.setReceivedExchange("exchange01");

        messageProperties.setReceivedRoutingKey("simple.routing");

        messageProperties.setHeader("method", "method1");

        messageProperties.setHeader("desc", "test");

        messageProperties.setHeader("__TypeId__", "package");

        messageProperties.setContentType("application/json");

        messageProperties.setContentEncoding("UTF-8");

        Message message = new Message(messsageBody, messageProperties);

        rabbitTemplate.send(message);
    }


    private Package generatePackage() {
        Package testPackage = new Package();

        testPackage.setId(1);

        testPackage.setDesc("Hello , there is nothing");

//        testPackage.setSendTime(LocalDateTime.now());

        return testPackage;
    }

}
