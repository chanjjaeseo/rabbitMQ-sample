package com.qcz.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitAdminTest {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private Queue queue01;

    @Autowired
    private DirectExchange exchange01;

    @Autowired
    private Binding binding01;

    @Test
    public void rabbitAdminTest() {
        rabbitAdmin.declareExchange(exchange01);

        rabbitAdmin.declareQueue(queue01);

        rabbitAdmin.declareBinding(binding01);

        // 加载@bean声明的Bean
//        rabbitAdmin.initialize();

//        rabbitAdmin.purgeQueue("queue01", false);
    }

}
