package org.vivus.activemq.interceptor;


public interface Interceptor<T> {
	void before(T context);

	void after(T context);
}
