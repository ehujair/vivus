package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.ddmlib.AdbHelper;
import org.vivus.nda.tools.entity.MacAddress;

public class WriteMacCmd implements ICommand<Integer> {
	AdbHelper adbHelper;
	MacAddress macAddress;

	public WriteMacCmd(AdbHelper adbHelper, MacAddress macAddress) {
		this.adbHelper = adbHelper;
		this.macAddress = macAddress;
	}

	@Override
	public Integer execute(CommandContext commandContext) {
		adbHelper.writeMac(macAddress.getMac());
		return commandContext.getMacAddressManager().insert(macAddress);
	}

}
