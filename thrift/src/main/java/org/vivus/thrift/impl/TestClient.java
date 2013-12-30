package org.vivus.thrift.impl;

import java.lang.reflect.Proxy;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.vivus.thrift.test.TestService;

public class TestClient {
	private static TTransport transport = new TSocket("127.0.0.1", 8911);
	private static TBinaryProtocol prot = new TBinaryProtocol(transport);;
	private static TestService.Iface testService;

	public static TestService.Iface getTestService() {
		if (testService == null) {
			testService = createProxy(TestService.Iface.class, new TestService.Client(prot));
		}
		return testService;
	}

	@SuppressWarnings("unchecked")
	private static <I> I createProxy(Class<I> iface, I target) {
		return (I) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new ClientInvocationHandler(transport, target));
	}
}
