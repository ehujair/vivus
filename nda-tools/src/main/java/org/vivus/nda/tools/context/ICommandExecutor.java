package org.vivus.nda.tools.context;

public interface ICommandExecutor {
	<T> T execute(ICommand<T> command);
}
