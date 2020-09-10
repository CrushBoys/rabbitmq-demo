package com.atuigu.rabbitmq.topic;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    public static final String QUEUE_NAME = "topic_queue_2";
    public static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //durable:队列持久化
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        // 绑定队列到交换机，同时指定需要订阅的routing key。假设此处需要update和delete消息
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.*");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);

//        channel.close();
//        connection.close();
    }
}
