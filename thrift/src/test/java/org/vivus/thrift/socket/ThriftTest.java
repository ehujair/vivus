package org.vivus.thrift.socket;

import org.apache.thrift.TException;
import org.junit.Test;
import org.vivus.thrift.impl.TestClient;
import org.vivus.thrift.test.TestService;

public class ThriftTest {
	TestService.Iface testService = TestClient.getTestService();

	@Test
	public void testNull() throws TException {
		testService.testNull();
	}
	
	@Test
	public void testFunc() throws TException {
		testService.testFunc();
	}
	
	@Test
	public void testResultFunc() throws TException {
		testService.resultFunc("", "");
	}
	
	@Test
	public void testIllegalArgumentException() throws TException {
		testService.resultFunc("1", "");
	}
	
	@Test
	public void testTException() throws TException {
		testService.resultFunc("2", "");
	}
}
