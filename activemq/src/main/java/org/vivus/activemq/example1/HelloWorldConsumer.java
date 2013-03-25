package org.vivus.activemq.example1;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloWorldConsumer implements Runnable, ExceptionListener {
	static int NUM = 0;
	static Object lock = new Object();

	@Override
	public synchronized void onException(JMSException arg0) {
		// TODO Auto-generated method stub
		System.out.println("JMS Exception occured.  Shutting down client.");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"linfenliang", "123456", "tcp://0.0.0.0:61616");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			// Destination destination = session.createQueue("TEST.FOO");
			Destination destination = session.createTopic("DEST");

			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			// Wait for a message
			Message message = consumer.receive();
			synchronized (lock) {
				System.out.println(NUM++);
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("Received: " + text);
				} else {
					System.out.println("_Received_null: " + message);
				}
			}

			consumer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

}