package org.vivus.nda.context;

public class CommandExecutorImpl extends CommandInterceptor {

	public <T> T execute(Command<T> command) {
		return command.execute(Context.peekCommandContext());
	}
}
