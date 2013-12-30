package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;

public class GetMacCmd implements ICommand<Integer> {

	@Override
	public Integer execute(CommandContext commandContext) {
		return commandContext.getConfiguration().getMacGenerator().generateMac();
	}

}
