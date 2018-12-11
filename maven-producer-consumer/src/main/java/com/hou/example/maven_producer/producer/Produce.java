package com.hou.example.maven_producer.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.hou.example.maven_producer.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Produce {
	private static final String QUEUE_NAME = "simple_test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1.获取一个连接
		Connection connection = ConnectionUtil.getConnection();
		// 2.从连接中获取一个通道
		Channel channel = connection.createChannel();
		// 3.声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 4.发布消息
		String msg = "我是生产者发出的消息！";
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("=============消息已发出===============");
		// 关闭通道和连接
		channel.close();
		connection.close();
	}

}
