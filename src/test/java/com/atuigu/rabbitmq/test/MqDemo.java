package com.atuigu.rabbitmq.test;

import com.atuigu.rabbitmq.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MqDemo {
    @Autowired
    private AmqpTemplate template;

    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.template.convertAndSend("spring.test.exchange","a.b",msg);
    }
}
