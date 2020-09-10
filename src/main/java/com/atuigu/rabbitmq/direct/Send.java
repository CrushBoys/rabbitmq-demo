package com.atuigu.rabbitmq.direct;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    public static final String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String message = "商品新增了， id = 1001";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
