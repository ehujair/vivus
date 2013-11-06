package org.vivus.nda.tools;

import java.util.Date;

import org.junit.Test;
import org.vivus.nda.tools.entity.MacAddress;
import org.vivus.nda.tools.impl.Engine;

public class MacAddressManagerTest extends NdaToolsTest {
	IMacAddressService macAddressService = engine.getMacAddressService();

	@Test
	public void testSave() {
		MacAddress macAddress = new MacAddress();
		macAddress.setMac(((Engine) engine).getConfiguration().getMacGenerator().generateMac());
		macAddress.setWriteTime(new Date());
		macAddressService.saveMacAddress(macAddress);
	}
}
