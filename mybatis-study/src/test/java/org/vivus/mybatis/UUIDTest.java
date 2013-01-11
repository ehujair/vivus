package org.vivus.mybatis;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {
	@Test
	public void testUUIDLength() {
		UUID randomUUID = UUID.randomUUID();
		String uuid = randomUUID.toString();
		System.out.println(uuid + ":" + uuid.length());
	}
}
