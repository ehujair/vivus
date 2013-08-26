package org.vivus.thrift.impl;

import java.lang.reflect.Proxy;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.thrift.socket.AbstractServer;
import org.vivus.thrift.socket.Server;
import org.vivus.thrift.test.TestService;
import org.vivus.thrift.test.TestService.Iface;

public class TestServer extends AbstractServer implements Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServer.class);
	TThreadPoolServer server;

	public TestServer(int port) {
		super(port);
	}

	@Override
	protected void start_() {
		try {
			Args args = new TThreadPoolServer.Args(new TServerSocket(port));
			args.processor(new TestService.Processor<Iface>(createProxy(TestService.Iface.class,
					new TestServiceImpl())));
//			args.processor(new TestService.Processor<Iface>(new TestServiceImpl()));
			args.protocolFactory(new TBinaryProtocol.Factory(true, true));
			LOGGER.info("Starting server on port " + port + " ...");
			server = new TThreadPoolServer(args);
			server.serve();
		} catch (Exception e) {
			LOGGER.error("can not start server", e);
		}
	}

	@Override
	protected void stop_() {
		if (server != null) {
			server.stop();
		}
	}

	@SuppressWarnings("unchecked")
	private static <I> I createProxy(Class<I> iface, I target) {
		return (I) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new ServerInvocationHandler(target));
	}

	public static void main(String[] args) {
		new TestServer(8911).start();
	}
}
