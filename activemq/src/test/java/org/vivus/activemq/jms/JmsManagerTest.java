package org.vivus.activemq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.vivus.activemq.BaseTest;

public class JmsManagerTest extends BaseTest{
	ConnectionFactory connectionFactory = configuration.getJmsConnectionFactory();
	
	/**
	 * 新开/修改医嘱,向相应的Topic发送消息
	 * @throws JMSException 
	 */
	@Test
	public void testMedicalOrders() throws JMSException {
		// 
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic("Topic");
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("msg");
		producer.send(message);
		connection.close();
	}
	
	/**
	 * 测试某人发到某人的消息
	 * @throws JMSException 
	 */
	@Test
	public void testP2P() throws JMSException {
		String userId = "lily";
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(userId);
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("msg");
		producer.send(message);
		connection.close();
	}
}
