package org.vivus.thrift.impl;

import org.apache.thrift.TException;
import org.vivus.thrift.test.Result;
import org.vivus.thrift.test.TestException;
import org.vivus.thrift.test.TestService;

public class TestServiceImpl implements TestService.Iface {
	@Override
	public void onewayFunc(String msg) throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public String testFunc() throws TException {
		// TODO Auto-generated method stub
		throw new RuntimeException("test throw runtime exception", new IllegalArgumentException(
				"cause by IllegalArgumentException"));
	}

	@Override
	public Result resultFunc(String id, String name) throws TestException, TException {
		// TODO Auto-generated method stub
		if ("1".equals(id)) {
			throw new IllegalArgumentException("test IllegalArgumentException");
		}
		if ("2".equals(id)) {
			throw new TException("test TException");
		}
		if ("3".equals(id)) {
			TestException exception = new TestException("TestException with cause");
			exception.setStackTrace(new RuntimeException("cause TestException").getStackTrace());
			throw exception;
		}
		throw new TestException("code-1");
	}

	@Override
	public String testNull() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
