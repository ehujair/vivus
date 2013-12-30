package org.vivus.activemq.jms.activemq;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.activemq.jms.JmsServer;

public class ActiveMQJmsServer implements JmsServer {
	static final Logger logger = LoggerFactory.getLogger(ActiveMQJmsServer.class);
	BrokerService brokerService;

	public ActiveMQJmsServer(BrokerService brokerService) {
		this.brokerService = brokerService;
	}

	@Override
	public void start() throws Exception {
		brokerService.start();
	}

	@Override
	public void stop() throws Exception {
		brokerService.stop();
	}
}
