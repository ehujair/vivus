package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.KeyValue;

public class LoadKeyValueCmd implements ICommand<KeyValue> {
	String key;

	public LoadKeyValueCmd(String key) {
		this.key = key;
	}

	@Override
	public KeyValue execute(CommandContext commandContext) {
		return commandContext.getKeyValueManager().load(key);
	}
}
