package org.vivus.adb;

import java.util.UUID;

public class DefaultMacGenerator implements MacGenerator {

	@Override
	public String generateMac() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(uuid.length() - 4);
	}

}
