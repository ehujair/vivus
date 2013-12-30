package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.KeyValue;
import org.vivus.nda.tools.entity.KeyValueManager;
import org.vivus.nda.tools.impl.KeyValueBlock;

public class FetchKeyValueBlockCmd implements ICommand<KeyValueBlock> {
	int fetchSize;
	String key;

	public FetchKeyValueBlockCmd(String key, int fetchSize) {
		this.key = key;
		this.fetchSize = fetchSize;
	}

	@Override
	public KeyValueBlock execute(CommandContext commandContext) {
		KeyValueManager keyValueManager = commandContext.getKeyValueManager();
		KeyValue keyValue = keyValueManager.load(key);
		int currentValue = keyValue.getCurrentValue();
		int lastValue = currentValue + fetchSize;
		keyValue.setCurrentValue(lastValue);
		keyValueManager.update(keyValue);
		return new KeyValueBlock(currentValue, lastValue - 1);
	}
}
