package com.atuigu.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory con = new ConnectionFactory();
        //设置服务地址
        con.setHost("192.168.10.100");
        //端口
        con.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        con.setUsername("zhangsan");
        con.setPassword("123456");
        con.setVirtualHost("/shopping");
        // 通过工程获取连接
        Connection connection = con.newConnection();

        return connection;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
