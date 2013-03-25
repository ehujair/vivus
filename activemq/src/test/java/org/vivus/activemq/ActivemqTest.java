package org.vivus.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActivemqTest {
	static final String url = "tcp://localhost:61616";
	static final String text = "Test message";
	static final String topic = "testTopic";

	static BrokerService brokerService = new BrokerService();
	static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

	@BeforeClass
	public static void beforeClass() {
		try {
			brokerService.addConnector(url);
			brokerService.setPersistent(true);
			brokerService.start();
			// 内嵌式启动，会在项目下产生activemq-data的目录，持久化消息会保存在该目录下的相应的KahaDB
		} catch (Exception e) {
			throw new RuntimeException("==== Start BrokerService occur exception ====", e);
		}
	}

	@Test
	public void test() throws InterruptedException {
		System.out.println("========== start ==========");
		Thread client = new Thread() {
			@Override
			public void run() {
				try {
					Connection connection = connectionFactory.createConnection();
					connection.start();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Destination destination = session.createTopic(topic);
					MessageConsumer consumer = session.createConsumer(destination);
					Message receive = consumer.receive();
					Assert.assertTrue(receive instanceof TextMessage);
					TextMessage textReceive = (TextMessage) receive;
					Assert.assertEquals(text, textReceive.getText());
					connection.close();
				} catch (Exception e) {
					throw new RuntimeException("==== receive message occur exception ====", e);
				}
			}
		};
		client.start();

		Thread.sleep(2000);
		Thread producer = new Thread() {
			@Override
			public void run() {
				try {
					Connection connection = connectionFactory.createConnection();
					connection.start();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Destination destination = session.createTopic(topic);
					MessageProducer producer = session.createProducer(destination);
					TextMessage message = session.createTextMessage(text);
					producer.send(message);
					connection.close();
				} catch (Exception e) {
					throw new RuntimeException("==== Send message occur exception ====", e);
				}
			}
		};
		producer.start();

		producer.join();
		client.join();
		System.out.println("========== finish ==========");
	}

	@Test
	public void test2() throws InterruptedException, JMSException {
		System.out.println("========== start ==========");
		Thread client = new Thread() {
			@Override
			public void run() {
				try {
					Connection connection = connectionFactory.createConnection();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Destination destination = session.createTopic(topic);
					MessageConsumer consumer = session.createConsumer(destination);
					// connection.start()必须在MessageConsumer.receive()调用前执行
					// 在接收信息时,必须被调用connection.start()
					connection.start();
					Message receive = consumer.receive();
					Assert.assertTrue(receive instanceof TextMessage);
					TextMessage textReceive = (TextMessage) receive;
					Assert.assertEquals(text, textReceive.getText());
					connection.close();
				} catch (Exception e) {
					throw new RuntimeException("==== receive message occur exception ====", e);
				}
			}
		};
		client.start();

		Thread.sleep(2000);
		Thread producer = new Thread() {
			@Override
			public void run() {
				try {
					Connection connection = connectionFactory.createConnection();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Destination destination = session.createTopic(topic);
					MessageProducer producer = session.createProducer(destination);
					TextMessage message = session.createTextMessage(text);
					// 发送信息时，调不调用connection.start()都无所谓，且放在MessageProducer.send(message)后调用也无问题
					// connection.start();
					producer.send(message);
					connection.close();
				} catch (Exception e) {
					throw new RuntimeException("==== Send message occur exception ====", e);
				}
			}
		};
		producer.start();

		producer.join();
		client.join();
		System.out.println("========== finish ==========");
	}

	/**
	 * 测试Queue的方式是否可以接收之前发送的消息
	 * <p>结论:可以
	 * 
	 * @throws InterruptedException
	 * @throws JMSException
	 */
	@Test
	public void testQueue() throws InterruptedException, JMSException {
		System.out.println("========== start ==========");
		Thread producer = new Thread() {
			@Override
			public void run() {
				try {
					Connection connection = connectionFactory.createConnection();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Destination destination = session.createQueue(topic);
					MessageProducer producer = session.createProducer(destination);
					TextMessage message = session.createTextMessage(text);
					// 发送信息时，调不调用connection.start()都无所谓，且放在MessageProducer.send(message)后调用也无问题
					// connection.start();
					producer.send(message);
					connection.close();
				} catch (Exception e) {
					throw new RuntimeException("==== Send message occur exception ====", e);
				}
			}
		};
		producer.start();
		producer.join();

		Thread client = new Thread() {
			@Override
			public void run() {
				try {
					Connection connection = connectionFactory.createConnection();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Destination destination = session.createQueue(topic);
					MessageConsumer consumer = session.createConsumer(destination);
					// connection.start()必须在MessageConsumer.receive()调用前执行
					// 在接收信息时,必须被调用connection.start()
					connection.start();
					Message receive = consumer.receive();
					Assert.assertTrue(receive instanceof TextMessage);
					TextMessage textReceive = (TextMessage) receive;
					Assert.assertEquals(text, textReceive.getText());
					System.out.println("Receive: " + textReceive.getText());
					connection.close();
				} catch (Exception e) {
					throw new RuntimeException("==== receive message occur exception ====", e);
				}
			}
		};
		client.start();
		client.join();
		System.out.println("========== finish ==========");
	}

	@Test
	public void testSub() throws InterruptedException, JMSException {
		System.out.println("========== start ==========");
		Producer producer = new Producer();
		Thread sender = new Thread(producer);
		sender.start();

		Thread.sleep(2000);
		Consumer consumer = new Consumer();
		Thread receiver = new Thread(consumer);
		receiver.start();

		Thread.sleep(4000);
		consumer.stop();
		producer.stop();
		sender.join();
		receiver.join();
		System.out.println("========== finish ==========");
	}

	/**
	 * 测试持久订阅
	 * 
	 * @throws InterruptedException
	 * @throws JMSException
	 */
	@Test
	public void testDurableSub() throws InterruptedException, JMSException {
		System.out.println("========== start ==========");
		// 第一次得先订阅，没产生订阅之前的消息不会收到；产生第一次订阅之后，才会接收到上次订阅关闭后到本次订阅激活前发送的消息
		Connection connection = connectionFactory.createConnection();
		connection.setClientID("consumer");
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic(topic);
		// 通过session.createDurableSubscriber()方法订阅
		session.createDurableSubscriber((Topic) destination, "TS");
		connection.close();

		Producer producer = new Producer();
		Thread sender = new Thread(producer);
		sender.start();

		Thread.sleep(2000);
		DurableConsumer consumer = new DurableConsumer();
		Thread receiver = new Thread(consumer);
		receiver.start();

		Thread.sleep(4000);
		consumer.stop();
		producer.stop();
		sender.join();
		receiver.join();
		System.out.println("========== finish ==========");
	}

	@AfterClass
	public static void afterClass() {
		try {
			brokerService.stop();
		} catch (Exception e) {
			throw new RuntimeException("==== End BrokerService occur exception ====", e);
		}
	}

	private static class Consumer implements Runnable {
		transient boolean stop = false;

		@Override
		public void run() {
			try {
				Connection connection = connectionFactory.createConnection();
				connection.setClientID("consumer");
				connection.start();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination destination = session.createTopic(topic);
				MessageConsumer consumer = session.createConsumer(destination);
				int count = 0;
				while (!stop && count < 9) {
					Message receive = consumer.receive();
					Assert.assertTrue(receive instanceof TextMessage);
					TextMessage textMessage = (TextMessage) receive;
					System.out.println(textMessage.getText());
					count++;
				}
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException("==== receive message occur exception ====", e);
			}
		}

		public void stop() {
			stop = true;
		}
	}

	private static class Producer implements Runnable {
		transient boolean stop = false;

		@Override
		public void run() {
			try {
				Connection connection = connectionFactory.createConnection();
				connection.start();
				int count = 0;
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination destination = session.createTopic(topic);
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				while (!stop) {
					if (count == 5) {
						Thread.sleep(3000); // 暂停3秒
					}
					TextMessage message = session.createTextMessage(text + count);
					producer.send(message);
					count++;
				}
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException("==== Send message occur exception ====", e);
			}
		}

		public void stop() {
			stop = true;
		}
	}

	private static class DurableConsumer implements Runnable {
		transient boolean stop = false;

		@Override
		public void run() {
			try {
				Connection connection = connectionFactory.createConnection();
				connection.setClientID("consumer");
				connection.start();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination destination = session.createTopic(topic);
				MessageConsumer subscriber = session.createDurableSubscriber((Topic) destination,
						"TS");
				int count = 0;
				while (!stop && count < 9) {
					Message receive2 = subscriber.receive();
					Assert.assertTrue(receive2 instanceof TextMessage);
					TextMessage textMessage2 = (TextMessage) receive2;
					System.out.println("TopicSubscriber: " + textMessage2.getText());
					count++;
				}
				connection.close();
			} catch (Exception e) {
				throw new RuntimeException("==== receive message occur exception ====", e);
			}
		}

		public void stop() {
			stop = true;
		}
	}
}
