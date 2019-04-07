package com.example.demo_rabbitmq_api.Demo.dxl;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Producer {


    public static void main(String[] args) throws Exception {

         ConnectionFactory factory = new ConnectionFactory();

         factory.setHost("192.168.236.128");
         factory.setPort(5672);
         factory.setVirtualHost("/");

         factory.setAutomaticRecoveryEnabled(true);

         Connection connection = factory.newConnection();
         Channel channel = connection.createChannel();

         String exchangeName = "test_dlx_exchange";


         String routingkey = "test_dlx.test";
         String msg ="test_dlx rabbitmq";

         AMQP.BasicProperties build = new AMQP.BasicProperties.Builder()
                 .expiration("10000")
                 .deliveryMode(2)
                 .contentEncoding("UTF-8")
                 .build();

         /**
          * mandatory的作用：
          *
          *         当mandatory标志位设置为true时，
          *         如果exchange根据自身类型和消息routingKey无法找到一个合适的queue存储消息，那么broker会调用basic.return方法将消息返还给生产者;当mandatory设置为false时，出现上述情况broker会直接将消息丢弃;通俗的讲，
          *         mandatory标志告诉broker代理服务器至少将消息route到一个队列中，
          *         否则就将消息return给发送者;
          */
         channel.basicPublish(exchangeName,routingkey,true,build,msg.getBytes());


    }


}
