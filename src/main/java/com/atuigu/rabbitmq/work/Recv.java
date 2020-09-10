package com.atuigu.rabbitmq.work;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    public static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String message = new String(body);
                System.out.println(message);
                //int i = 1 / 0;
                try {
                    // 模拟完成任务的耗时：1000ms
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                // 手动进行ACK
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //channel.close();
        //connection.close();
    }
}
