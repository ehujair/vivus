package org.vivus.nda.tools.impl;

import org.vivus.nda.tools.cmd.FetchKeyValueBlockCmd;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.context.IIdGenerator;
import org.vivus.nda.tools.entity.KeyValue;

public class DbIdGenerator implements IIdGenerator {
	protected int fetchSize = 20;
	protected int nextValue = 0;
	protected int lastValue = -1;
	ICommandExecutor commandExecutor;

	public DbIdGenerator(ICommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	protected synchronized void fetch() {
		KeyValueBlock block = commandExecutor.execute(new FetchKeyValueBlockCmd(KeyValue.KEY_ID,
				fetchSize));
		nextValue = block.getNextValue();
		lastValue = block.getLastValue();
	}

	@Override
	public synchronized String generateId() {
		if (lastValue < nextValue) {
			fetch();
		}
		return Long.toString(nextValue++);
	}
}
