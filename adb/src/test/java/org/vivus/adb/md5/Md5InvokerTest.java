package org.vivus.adb.md5;

import org.junit.Test;

public class Md5InvokerTest {
	@Test
	public void testEncrypt() {
		System.out.println(Md5Invoker.encrypt("test encrypt"));
	}
}
