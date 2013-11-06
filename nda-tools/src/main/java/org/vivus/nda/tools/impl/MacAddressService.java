package org.vivus.nda.tools.impl;

import org.vivus.nda.tools.IMacAddressService;
import org.vivus.nda.tools.cmd.SaveMacAddressCmd;
import org.vivus.nda.tools.entity.MacAddress;

public class MacAddressService extends AService implements IMacAddressService {
	public int saveMacAddress(MacAddress macAddress) {
		return getCommandExecutor().execute(new SaveMacAddressCmd(macAddress));
	}
}
