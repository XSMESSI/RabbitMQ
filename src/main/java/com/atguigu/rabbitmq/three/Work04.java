package com.atguigu.rabbitmq.three;
/**
    *@Author: Leo messi
    *@Date: 2022/07/02/16:38
    *@Description:
    */

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.atguigu.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息在手动应答时是不丢失，放回队列中重新消费11
 */
public class Work04 {

    //队列名称
    public static final String TASK_QUEUE_NAME = "ack_queue";

    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2等待接收消息处理时间较长");


        DeliverCallback deliverCallback = (consumerTag,message) ->{
            //沉睡1S
            SleepUtils.sleep(3);
            System.out.println("接收到的消息：" + new String(message.getBody(),"UTF-8"));
            //手动应答
            /**
             * 1.消息的标记：tag
             * 2.是否批量应答 false : 不批量应答信道中的消息 true:批量
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);

        };

        //采用手动应答
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag->{
            System.out.println("消费者取消消费接口回调");
        }));
    }
}
