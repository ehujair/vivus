package org.vivus.activemq.thread;

public class ThreadJoinTest {
	public static void main(String[] args) {
		System.out.println("Start main: " + Thread.currentThread().getName());
		int num = 5;
		Thread[] threads = new Thread[num];
		for (int i = 0; i < num; i++) {
			threads[i] = new Thread(new Join());
			threads[i].start();
		}
		try {
			for (int i = 0; i < num; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException("==== Thread join occur exception ====", e);
		}
		System.out.println("End main: " + Thread.currentThread().getName());
	}

	static class Join implements Runnable {
		@Override
		public void run() {
			System.out.println("Join run: " + Thread.currentThread().getName());
		}
	}
}
