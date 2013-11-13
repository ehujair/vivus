package org.vivus.adb;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ThreadTest {
	@Test
	public void testThreadGroup() {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		System.out.println(threadGroup);
	}

	@Test
	public void testHandleUncaughtException() {
		final List<Throwable> exceptions = new ArrayList<Throwable>();
		Thread subThread = new Thread(new Runnable() {
			@Override
			public void run() {
				throw new RuntimeException("subThread unchecked exception");
			}
		});
		subThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				exceptions.add(e);
			}
		});
		subThread.start();
		try {
			subThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (exceptions.size() > 0) {
			for (Throwable t : exceptions) {
				t.printStackTrace();
			}
			Assert.fail();
		}
	}
}
