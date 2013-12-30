package org.vivus.activemq.example1;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloWorldProducer implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// 创建连接工厂//vm://localhost
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"linfenliang", "123456", "tcp://0.0.0.0:61616");

			// 创建连接
			Connection connection = connectionFactory.createConnection();
			connection.start();
			/**
			 * JMS 消息只有在被确认之后，才认为已经被成功地消费了。消息的成功消费通 常包含三个阶段：客户接收消息、客户处理消息和消息被确认。
			 * 在事务性会话中，当一个事务被提交的时候，确认自动发生。在非事务性会
			 * 话中，消息何时被确认取决于创建会话时的应答模式（acknowledgement mode）。 session的应答模式
			 * Session.AUTO_ACKNOWLEDGE;
			 * 当客户成功的从receive方法返回的时候，或者从MessageListener.onMessage
			 * 方法成功返回的时候，会话自动确认客户收到的消息。 Session.CLIENT_ACKNOWLEDGE;
			 * 客户通过消息的acknowledge
			 * 方法确认消息。需要注意的是，在这种模式中，确认是在会话层上进行：确认一个被消费的消息将自动确认所有已被会话消费的消息
			 * 。例如，如果一个消息消费者消费了10 个消息，然后确认第5 个消息，那么所有10 个消息都被确 认。
			 * Session.DUPS_OK_ACKNOWLEDGE; 该选择只是会话迟钝的确认消息的提交。如果JMS provider
			 * 失败，那么可能会导致一些重复的消息。如果是重复的消息，那么JMS provider 必须把消息头的JMSRedelivered
			 * 字段设置为 true。
			 **/
			// 创建Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			/**
			 * 在点对点消息传递域中，目的地被成为队列（queue）；在发布/订阅消息传递 域中，目的地被成为主题（topic）。
			 */
			// 创建消息的目的地
			// Destination destination = session.createQueue("TEST.FOO");
			Destination destination = session.createTopic("DEST");
			// 从消息对象会话中创建消息的生产者用于把消息发送到目的地（queue或topic）
			MessageProducer producer = session.createProducer(destination);
			/**
			 * 消息的提交模式分两种 PERSISTENT。指示JMS provider 持久保存消息，以保证消息不会因为JMS provider
			 * 的失败而丢失。 · NON_PERSISTENT。不要求JMS provider 持久保存消息。
			 */
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			// 生成的消息内容
			String text = "Hello world! From: " + Thread.currentThread().getName() + " : "
					+ this.hashCode();
			/**
			 * JMS 消息由以下三部分组成： 消息头。每个消息头字段都有相应的getter 和setter 方法。
			 * 消息属性。如果需要除消息头字段以外的值，那么可以使用消息属性。 消息体。JMS
			 * 定义的消息类型有TextMessage、MapMessage、BytesMessage、StreamMessage 和
			 * ObjectMessage。
			 */
			TextMessage message = session.createTextMessage(text);

			System.out.println("Sent message: " + Thread.currentThread().getName() + " : "
					+ message.hashCode());
			// 生产者开始生产消息
			producer.send(message);
			// 关闭
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}

	}

}