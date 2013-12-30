package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.MacAddress;

public class SaveMacAddressCmd implements ICommand<Integer> {
	MacAddress macAddress;

	public SaveMacAddressCmd(MacAddress macAddress) {
		this.macAddress = macAddress;
	}

	@Override
	public Integer execute(CommandContext commandContext) {
		return commandContext.getMacAddressManager().insert(macAddress);
	}

}
