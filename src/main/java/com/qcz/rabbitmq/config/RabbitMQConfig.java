package com.qcz.rabbitmq.config;

import com.qcz.rabbitmq.convert.ImageMessageConverter;
import com.qcz.rabbitmq.convert.PDFMessageConverter;
import com.qcz.rabbitmq.convert.TextMessageConverter;
import com.qcz.rabbitmq.listener.MessageDelegate;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan({"com.qcz.rabbitmq.*"})
public class RabbitMQConfig {

    @Autowired
    private Globals globals;

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setVirtualHost(globals.getVirtualHost());
//        connectionFactory.setAddresses(globals.getRabbitAddress());
//        connectionFactory.setUsername(globals.getUsername());
//        connectionFactory.setPassword(globals.getPassword());
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//        connectionFactory.setChannelCacheSize(globals.getChannelCacheSize());
//        connectionFactory.setChannelCheckoutTimeout(globals.getChannelCheckoutTimeOut());
//        connectionFactory.setPublisherConfirms(globals.isPublisherConfirms());
//        connectionFactory.setPublisherReturns(globals.isPublisherReturns());
//        return connectionFactory;
//    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(globals.isMandatory());
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
//        // container会自动确认消息(emmm...我也不知道为啥)
//        // 如果你要手动确认消息实现 ChannelAwareMessageListener 然后绑到container的listener上
////        container.setAcknowledgeMode(AcknowledgeMode.NONE);
//        // 监听的队列
//        container.setQueueNames("queue01", "queue02");
//        // 启动后启动两个线程作为消费者
//        container.setConcurrentConsumers(2);
//        container.setPrefetchCount(3);
//        // 默认实现的监听器去处理
////        container.setMessageListener(message -> {
////            System.out.println("Received Message :" + new String(message.getBody()));
////            System.out.println("Message Id :" + message.getMessageProperties().getMessageId());
////        });
//        // 采用adapter实现自己的策略去处理
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageDelegate());
//
//        // Convert1 : json => Map
////        messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
//        // Convert2 : json => Object
////        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter("*");
////        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
////        javaTypeMapper.setTrustedPackages("*");
////        // 对__TypeId__进行映射处理
////        Map<String, Class<?>> idClassMapping = new HashMap<>();
////        idClassMapping.put("package", com.qcz.rabbitmq.pojo.Package.class);
////        idClassMapping.put("order", com.qcz.rabbitmq.pojo.Order.class);
////        javaTypeMapper.setIdClassMapping(idClassMapping);
////        converter.setJavaTypeMapper(javaTypeMapper);
//        // Convert3: 全局转换器
//        ContentTypeDelegatingMessageConverter converter = new ContentTypeDelegatingMessageConverter();
//
//        SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
//        converter.addDelegate("text", simpleMessageConverter);
//        converter.addDelegate("text/plain", simpleMessageConverter);
//        converter.addDelegate("application/x-java-serialized-object", simpleMessageConverter);
//
//        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
//        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
//        javaTypeMapper.setTrustedPackages("*");
//        Map<String, Class<?>> idClassMapping = new HashMap<>();
//        idClassMapping.put("package", com.qcz.rabbitmq.pojo.Package.class);
//        idClassMapping.put("order", com.qcz.rabbitmq.pojo.Order.class);
//        javaTypeMapper.setIdClassMapping(idClassMapping);
//        jsonMessageConverter.setJavaTypeMapper(javaTypeMapper);
//        converter.addDelegate("application/json", jsonMessageConverter);
//        converter.addDelegate("json", jsonMessageConverter);
//
//        ImageMessageConverter imageMessageConverter = new ImageMessageConverter();
//        converter.addDelegate("image/png", imageMessageConverter);
//        converter.addDelegate("image", imageMessageConverter);
//
//        PDFMessageConverter pdfMessageConverter = new PDFMessageConverter();
//        converter.addDelegate("application/pdf", pdfMessageConverter);
//
//        messageListenerAdapter.setMessageConverter(converter);
//
//        Map<String, String> queueMethodNameMap = new HashMap<>();
//        queueMethodNameMap.put("queue01", "handleMessage");
//        queueMethodNameMap.put("queue02", "handleMessage2");
//        messageListenerAdapter.setQueueOrTagToMethodName(queueMethodNameMap);
//
//        container.setMessageListener(messageListenerAdapter);
//        return container;
//    }



}
