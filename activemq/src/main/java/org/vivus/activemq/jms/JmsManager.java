package org.vivus.activemq.jms;

import javax.jms.Destination;
import javax.jms.Message;

public interface JmsManager {
	void send(Destination destination, Message message);

	Message receive();
}
