package com.example.demo_rabbitmq_api.Demo.dxl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) throws Exception {

        // this is my edit
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.236.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        factory.setAutomaticRecoveryEnabled(true);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_dlx_exchange";
        String routingKey = "test_dlx.#";


        // 创建exchange
        channel.exchangeDeclare(exchangeName,"topic",true);

        // 声明一个队列
        String queueName = "test_dlx_queue";

        Map<String, Object> map = new HashMap<>();
        // 设置死信队列的参数
        map.put("x-dead-letter-exchange","test_dlx.trueexchange");
        channel.queueDeclare(queueName,true,false
        ,false,map);

        // 绑定队列
        channel.queueBind(queueName,exchangeName,routingKey);


        // 声明一个死信队列
        String dlxexchangeName = "test_dlx.trueexchange";
        channel.exchangeDeclare(dlxexchangeName,"topic",true);
        String dlxqueueName = "test_dlx_truequeue";
        channel.queueDeclare(dlxqueueName,true,false,false,null);

        channel.queueBind(dlxqueueName,dlxexchangeName,"#");


        channel.basicConsume(queueName,true,new MyConsumer(channel));


    }

}
