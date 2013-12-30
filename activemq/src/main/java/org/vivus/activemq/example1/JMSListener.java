package org.vivus.activemq.example1;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

public class JMSListener extends HttpServlet implements ServletContextListener, Serializable,
		MessageListener {
	private static final long serialVersionUID = -6297114667498310097L;

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			InitialContext initctx = new InitialContext();
			Context envContext = (Context) initctx.lookup("java:comp/env");
			ConnectionFactory connectionFactory = (ConnectionFactory) envContext
					.lookup("jms/FailoverConnectionFactory");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			connection.setClientID("jerry");
			Session jmsSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber consumer = jmsSession.createDurableSubscriber(
					(Topic) envContext.lookup("jms/topic/MyTopic"), "MySub");
			consumer.setMessageListener((MessageListener) this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String checkText(Message m, String s) {
		try {
			return m.getStringProperty(s);
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void onMessage(Message message) {
		if (checkText(message, "RefreshArticleId") != null) {
			String articleId = checkText(message, "RefreshArticleId");
			System.out.println("接收刷新文章消息，开始刷新文章ID=" + articleId);
		}
		if (checkText(message, "RefreshThreadId") != null) {
			String articleId = checkText(message, "RefreshThreadId");
			System.out.println("接收刷新线程消息，开始刷新线程ID=" + articleId);
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

}