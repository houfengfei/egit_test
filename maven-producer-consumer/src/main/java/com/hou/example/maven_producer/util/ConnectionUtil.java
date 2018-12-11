package com.hou.example.maven_producer.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	/**
	 * 获取rabbitMQ的连接
	 * @return hechuanbin
	 */
	public static Connection getConnection() throws IOException, TimeoutException {
		// 1.定义连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		// 2.设置服务地址
		connectionFactory.setHost("localhost");
		// 3.设置端口 5672
		connectionFactory.setPort(5672);
		// 4.设置虚拟主机
		connectionFactory.setVirtualHost("/");
		// 5.设置用户名
		connectionFactory.setUsername("guest");
		// 6.设置密码
		connectionFactory.setPassword("guest");
		return connectionFactory.newConnection();
	}

}
