package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Leo messi
 * @Date: 2022/07/02/15:17
 * @Description: 这是一个工作线程（相当于之前消费者）
 */
public class Worker01 {

    //队列名称
    public static final String QUEUE_NAME = "hello";

    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        //消息的接收
        DeliverCallback deliverCallback = (consumerTag,message) ->{
            System.out.println("接收的消息:" + new String(message.getBody()));
        };

        //消息接收被取消时，执行下面的内容
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println(consumerTag + "消息这取消消费接口回调逻辑");
        };

        //消息的接收
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答true代表的自动应答false代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        System.out.println("C2等待接收消息....");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
