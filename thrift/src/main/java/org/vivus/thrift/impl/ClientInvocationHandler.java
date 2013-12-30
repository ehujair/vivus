package org.vivus.thrift.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientInvocationHandler implements InvocationHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerInvocationHandler.class);

	TTransport transport;
	Object target;

	public ClientInvocationHandler(TTransport transport, Object target) {
		this.transport = transport;
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			transport.open();
			return method.invoke(target, args);
		} catch (Exception e) {
			LOGGER.error("original: ", e);
			Throwable cause = e;
			Set<Throwable> dejaVu = Collections
					.newSetFromMap(new IdentityHashMap<Throwable, Boolean>());
			dejaVu.add(cause);
			while (cause.getCause() != null) {
				if (dejaVu.contains(cause.getCause())) {
					LOGGER.info("\tCIRCULAR " + cause + ", cause [" + cause.getCause()
							+ "]");
					break;
				}
				cause = cause.getCause();
				dejaVu.add(cause);
			}
			if (cause instanceof TApplicationException) {
				if (((TApplicationException) cause).getType() == TApplicationException.MISSING_RESULT) {
					return null;
				}
			}
			throw cause;
		} finally {
			transport.close();
		}
	}
}
