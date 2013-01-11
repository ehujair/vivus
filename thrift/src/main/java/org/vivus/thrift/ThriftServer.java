package org.vivus.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.AbstractServerArgs;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftServer {
	static Logger logger = LoggerFactory.getLogger(ThriftServer.class);

	public static void main(String[] args) {
		TServerTransport transport = null;
		try {
			transport = new TServerSocket(8000);
		} catch (TTransportException e) {
			logger.error("Create TServerTransport error", e);
		}
		AbstractServerArgs<?> asArgs = new TServer.Args(transport);
		TServer server = new TSimpleServer(asArgs);
		server.serve();
	}
}
