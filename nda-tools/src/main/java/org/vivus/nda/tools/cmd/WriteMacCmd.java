package org.vivus.nda.tools.cmd;

import java.util.Date;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.ddmlib.AdbHelper;
import org.vivus.nda.tools.entity.MacAddress;

public class WriteMacCmd implements ICommand<Void> {
	AdbHelper adbHelper;

	public WriteMacCmd(AdbHelper adbHelper) {
		this.adbHelper = adbHelper;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		int mac = commandContext.getConfiguration().getMacGenerator().generateMac();
		adbHelper.writeMac(mac);
		MacAddress macAddress = new MacAddress();
		macAddress.setMac(mac);
		macAddress.setWriteTime(new Date());
		commandContext.getMacAddressManager().insert(macAddress);
		return null;
	}

}
