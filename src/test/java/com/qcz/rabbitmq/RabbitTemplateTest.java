package com.qcz.rabbitmq;

import com.qcz.rabbitmq.pruducer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTemplateTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Sender sender;

    @Test
    public void rabbitTemplateTest() {
        // send message ~ Method 1
        // 指定交换机、路由键 自定义消息属性
        MessageProperties messageProperties = new MessageProperties();

        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);

        messageProperties.setMessageId("message_" + UUID.randomUUID().toString());

        messageProperties.setReceivedExchange("exchange01");

        messageProperties.setReceivedRoutingKey("simple.routing");

        messageProperties.setHeader("method", "method1");

        messageProperties.setHeader("desc", "test");

        messageProperties.setContentType("text/plain");

        messageProperties.setContentType("UTF-8");

        Message message = new Message("Hello Rabbit".getBytes(), messageProperties);

        rabbitTemplate.send(message);

        // send message ~ Method 2
        // 指定交换机、路由键,不定义消息属性, 且消息发送对象类型(会转化成byte类型)
//        rabbitTemplate.convertAndSend("exchange01", "simple.routing", "Hello Rabbit");

        // send message ~ Method 3
        // 采用rabbitTemplate定义的交换机、路由键来发送消息
//        rabbitTemplate.convertAndSend((Object) "Hello Rabbit", message -> {
//            Map<String, Object> header =  message.getMessageProperties().getHeaders();
//            header.put("SenderName", "xiaoquan");
//            return message;
//        });

    }

    @Test
    public void returnCallbackTest() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("desc","test");
        properties.put("sender","xiaoquan");
        for(int i = 0; i < 100; i++) {
            sender.send("Hello Rabbit", properties);
        }
    }

}
