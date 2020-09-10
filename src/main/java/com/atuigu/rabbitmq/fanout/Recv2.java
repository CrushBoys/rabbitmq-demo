package com.atuigu.rabbitmq.fanout;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    public static final String QUEUE_NAME = "fanout_queue_2";
    public static final String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [消费者2] received : " + message + "!");
            }
        };
        // 监听队列，自动返回完成
        channel.basicConsume(QUEUE_NAME,true,consumer);

        //channel.close();
        //connection.close();
    }
}
