package org.vivus.activemq.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.vivus.activemq.interceptor.Interceptor;

public class JmsInterceptor implements Interceptor<JmsContext> {

	@Override
	public void before(JmsContext jmsContext) {
		Connection connection;
		try {
			connection = jmsContext.getConfiguration().getJmsConnectionFactory()
					.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(jmsContext.getId());
		MessageProducer producer = session.createProducer(destination);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void after(JmsContext context) {
		// TODO Auto-generated method stub
		
	}

}
