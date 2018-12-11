package com.hou.example.maven_producer.consumer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import com.hou.example.maven_producer.util.ConnectionUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/**
 *  * 获取消息队列中的消息  * @author houff  
 */
public class Consumer {
	private static final String QUEUE_NAME = "simple_test";

	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		// 1.获取连接
		Connection connection = ConnectionUtil.getConnection();
		// 2.在连接中获取一个通道
		Channel channel = connection.createChannel();
		/* 方式一：低版本
		 3.定义队列的消费者
		 QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
		 监听队列
		 channel.basicConsume(QUEUE_NAME,true,queueingConsumer);
		 while(true) 
		 {
		 Delivery nextDelivery = queueingConsumer.nextDelivery();
		 String msg = new String(nextDelivery.getBody());
		 System.out.println("++++++++++:"+msg);
		 }*/
		// 方式二：
		// 3.队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 当生产者向队列发送消息的时候会触发该类
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties basicProperties,
					byte[] body) throws UnsupportedEncodingException {
				String msg = new String(body, "utf-8");
				System.out.println("===========:" + msg);
			}
		};
		// 监听队列
		channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
	}
}
