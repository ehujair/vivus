package org.vivus.activemq.jms;

import org.vivus.activemq.Configuration;

public class JmsContext {
	String id;
	Configuration configuration;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	
}
