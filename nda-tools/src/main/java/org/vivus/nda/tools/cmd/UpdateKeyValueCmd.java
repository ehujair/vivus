package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.KeyValue;

public class UpdateKeyValueCmd implements ICommand<Void> {
	KeyValue keyValue;

	public UpdateKeyValueCmd(KeyValue keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		commandContext.getKeyValueManager().update(keyValue);
		return null;
	}

}
