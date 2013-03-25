package org.vivus.activemq.example1;

import java.io.IOException;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			InitialContext initCtx = new InitialContext();
			Context envContext = (Context) initCtx.lookup("java:comp/env");
			ConnectionFactory connectionFactory = (ConnectionFactory) envContext
					.lookup("jms/NormalConnectionFactory");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session jmsSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = jmsSession.createProducer((Destination) envContext
					.lookup("jms/topic/MyTopic"));
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			Message testMessage = jmsSession.createMessage();
			testMessage.setStringProperty("RefreshArticleId", "2046");
			producer.send(testMessage);
			testMessage.clearProperties();
			testMessage.setStringProperty("RefreshThreadId", "331");
			producer.send(testMessage);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		String arg1 = request.getParameter("arg1");
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		Date responseTimestamp = new Date();
		response.getWriter().write(
				"<response><r><d id='a'><e id='a'>" + arg1 + responseTimestamp + "</e></d></r>"
						+ "</response>");
	}

}