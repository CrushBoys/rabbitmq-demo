package com.atuigu.rabbitmq.work;

import com.atuigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建信道
        Channel channel = connection.createChannel();
        //3.声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i < 50; i++) {
            //定义消息内容
            String message = "樱桃" + i;
            //4.向指定的队列中发送消息
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        //5.关闭信道，连接
        channel.close();
        connection.close();
    }
}
