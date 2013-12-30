package org.vivus.nda.tools.impl;

import org.vivus.nda.tools.context.ICommandExecutor;

public abstract class AService {
	ICommandExecutor commandExecutor;

	public ICommandExecutor getCommandExecutor() {
		return commandExecutor;
	}

	public void setCommandExecutor(ICommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}
}
