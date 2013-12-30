package org.vivus.nda.context;

public interface CommandExecutor {
	<T> T execute(Command<T> command);
}
