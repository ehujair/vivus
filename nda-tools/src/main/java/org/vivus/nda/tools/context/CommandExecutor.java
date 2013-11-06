package org.vivus.nda.tools.context;

public class CommandExecutor extends ACommandInterceptor {

	public <T> T execute(ICommand<T> command) {
		return command.execute(Context.peekCommandContext());
	}
}
