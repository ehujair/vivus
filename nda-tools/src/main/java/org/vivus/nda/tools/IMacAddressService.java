package org.vivus.nda.tools;

import org.vivus.nda.tools.ddmlib.AdbHelper;
import org.vivus.nda.tools.entity.MacAddress;

public interface IMacAddressService {
	int getMac();

	int saveMacAddress(MacAddress macAddress);

	int writeMac(AdbHelper adbHelper, MacAddress macAddress);
}
