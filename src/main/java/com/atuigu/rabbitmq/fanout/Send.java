package com.atuigu.rabbitmq.fanout;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static final String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String message = "樱桃";
        // 发布消息到Exchange
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
