package org.vivus.activemq.example1;

import org.apache.activemq.broker.BrokerService;

public class App {
	static final String url = "tcp://0.0.0.0:61616";

	public static void main(String[] args) {
		BrokerService brokerService = new BrokerService();
		try {
			brokerService.addConnector(url);
			brokerService.start();
		} catch (Exception e) {
			throw new RuntimeException("==== Start BrokerService occur exception ====", e);
		}
		Thread producer = thread(new HelloWorldProducer(), false);
		producer.start();
		int num = 5;
		Thread[] threads = new Thread[num];
		for (int i = 0; i < num; i++) {
			threads[i] = thread(new HelloWorldConsumer(), false);
			threads[i].start();
		}
		try {
			producer.join();
			for (int i = 0; i < num; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException("==== Thread join occur exception ====", e);
		}

		try {
			brokerService.stop();
		} catch (Exception e) {
			throw new RuntimeException("==== Stop BrokerService occur exception ====", e);
		}
	}

	public static Thread thread(Runnable runnable, boolean daemon) {
		Thread thread = new Thread(runnable);
		thread.setDaemon(daemon);
//		thread.start();
		return thread;
	}
}