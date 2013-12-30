package org.vivus.activemq.event;

public interface EventHandler<T extends Event> {
	void handle(T event);
}
