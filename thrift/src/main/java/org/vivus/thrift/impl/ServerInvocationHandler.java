package org.vivus.thrift.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.thrift.test.TestException;

public class ServerInvocationHandler implements InvocationHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerInvocationHandler.class);

	Object target;

	public ServerInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		LOGGER.info("ServerInvocationHandler: {}.{}", method.getDeclaringClass().getName(),
				method.getName());
		try {
			return method.invoke(target, args);
		} catch (Throwable t) {
			LOGGER.error("", t);
			Throwable cause = t;
			Set<Throwable> dejaVu = Collections
					.newSetFromMap(new IdentityHashMap<Throwable, Boolean>());
			while (cause != null) {
				dejaVu.add(cause);
				if (dejaVu.contains(cause.getCause())) {
					LOGGER.info("\tCIRCULAR " + cause + ", cause [" + cause.getCause()
							+ "]");
					break;
				}
				if (cause instanceof TestException) {
					LOGGER.info("cause is instanceof TestException");
					throw cause;
				}
				cause = cause.getCause();
			}
			LOGGER.info("cause isn't instanceof TestException");
			throw new TestException(ExceptionUtils.getStackTrace(t));
		}
	}
}
