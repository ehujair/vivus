package org.vivus.thrift.socket;

import org.junit.BeforeClass;
import org.junit.Test;

public class SocketTest {
	static final String HOST = "127.0.0.1";
	static final int PORT = 8000;

	@BeforeClass
	public static void beforeClass() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Server server = new SingleSocketServer(PORT);
				server.start();
			}
		};
		thread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSingle() {
		Client socketClient = new SocketClient(HOST, PORT);
		socketClient.invoke();
	}

	@Test
	public void testSingleWithMulti() {
		final Client client1 = new SocketClient(HOST, PORT);
		final Client client2 = new SocketClient(HOST, PORT);
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				client1.invoke();
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				client2.invoke();
			}
		};
		thread1.start();
		thread2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
