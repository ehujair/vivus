package org.vivus.thrift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ThriftServer implements Server {
	static final Logger logger = LoggerFactory.getLogger(ThriftServer.class);
	protected int port = 8911;

	public ThriftServer() {
	}

	public ThriftServer(int port) {
		this.port = port;
	}
}
