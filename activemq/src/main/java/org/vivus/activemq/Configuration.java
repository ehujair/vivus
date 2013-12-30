package org.vivus.activemq;

import javax.jms.ConnectionFactory;

import org.vivus.activemq.jms.JmsServer;

public class Configuration {
	String jmsUrl;
	int jmsPort;
	ConnectionFactory jmsConnectionFactory;
	JmsServer jmsServer;
	boolean jmsStart;

	JmsService jmsService;

	public String getJmsUrl() {
		return jmsUrl;
	}

	public void setJmsUrl(String jmsUrl) {
		this.jmsUrl = jmsUrl;
	}

	public int getJmsPort() {
		return jmsPort;
	}

	public void setJmsPort(int jmsPort) {
		this.jmsPort = jmsPort;
	}

	public ConnectionFactory getJmsConnectionFactory() {
		return jmsConnectionFactory;
	}

	public void setJmsConnectionFactory(ConnectionFactory jmsConnectionFactory) {
		this.jmsConnectionFactory = jmsConnectionFactory;
	}

	public JmsServer getJmsServer() {
		return jmsServer;
	}

	public void setJmsServer(JmsServer jmsServer) {
		this.jmsServer = jmsServer;
	}

	public boolean isJmsStart() {
		return jmsStart;
	}

	public void setJmsStart(boolean jmsStart) {
		this.jmsStart = jmsStart;
	}

	public JmsService getJmsService() {
		return jmsService;
	}

	public void setJmsService(JmsService jmsService) {
		this.jmsService = jmsService;
	}
	
	
}
