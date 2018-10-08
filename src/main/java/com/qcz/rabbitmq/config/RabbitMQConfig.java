package com.qcz.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


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

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container =  new SimpleMessageListenerContainer(connectionFactory);
////        container.set
//        return container;
//    }


}
