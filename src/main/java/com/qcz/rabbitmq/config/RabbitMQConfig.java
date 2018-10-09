package com.qcz.rabbitmq.config;

import com.qcz.rabbitmq.listener.MessageDelegate;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan({"com.qcz.rabbitmq.*"})
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAddresses("132.232.141.241");
        connectionFactory.setUsername("xiaoquan");
        connectionFactory.setPassword("xiaoquan123");
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        connectionFactory.setChannelCacheSize(16);
        connectionFactory.setChannelCheckoutTimeout(180);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("exchange01");
        rabbitTemplate.setRoutingKey("simple.routing");
        return rabbitTemplate;
    }


    /**
    // declare exchange
    @Bean
    public DirectExchange exchange01() {
        return new DirectExchange("exchange01", true, false);
    }

    // declare queue
    @Bean
    public Queue queue01() {
        return new Queue("queue01", true);
    }

    // declare binding
    @Bean
    public Binding binding01() {
        return new Binding("queue01",
                Binding.DestinationType.QUEUE,
                "exchange01", "simple.routing", new HashMap<>());
    }
    */

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container =  new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("queue01", "queue02");
        container.setConcurrentConsumers(2);
        container.setPrefetchCount(3);
//        container.setMessageListener(message -> {
//            System.out.println("Received Message :" + new String(message.getBody()));
//            System.out.println("Message Id :" + message.getMessageProperties().getMessageId());
//        });
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
        Map<String, String> queueMethodNameMap = new HashMap<>();
        queueMethodNameMap.put("queue01", "handleMessage");
        queueMethodNameMap.put("queue02", "handleMessage2");
        messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
        messageListenerAdapter.setQueueOrTagToMethodName(queueMethodNameMap);
        container.setMessageListener(messageListenerAdapter);
//        container.setMessageConverter();
        return container;
    }



}
