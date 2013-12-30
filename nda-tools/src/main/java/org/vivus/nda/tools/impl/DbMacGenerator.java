package org.vivus.nda.tools.impl;

import org.vivus.nda.tools.cmd.FetchKeyValueBlockCmd;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.context.IMacGenerator;
import org.vivus.nda.tools.entity.KeyValue;

public class DbMacGenerator implements IMacGenerator {
	protected int fetchSize = 1;
	protected int nextValue = 0;
	protected int lastValue = -1;
	ICommandExecutor commandExecutor;

	public DbMacGenerator(ICommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	@Override
	public synchronized int generateMac() {
		if (lastValue < nextValue) {
			fetch();
		}
		return nextValue++;
	}

	protected synchronized void fetch() {
		KeyValueBlock block = commandExecutor.execute(new FetchKeyValueBlockCmd(
				KeyValue.KEY_MAC_ADDRESS, fetchSize));
		nextValue = block.getNextValue();
		lastValue = block.getLastValue();
	}
}
