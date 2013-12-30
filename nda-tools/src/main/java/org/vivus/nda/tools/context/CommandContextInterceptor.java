package org.vivus.nda.tools.context;

import org.vivus.nda.tools.config.AConfiguration;

public class CommandContextInterceptor extends ACommandInterceptor {
	AConfiguration configuration;

	public CommandContextInterceptor(AConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public <T> T execute(ICommand<T> command) {
		CommandContext commandContext = new CommandContext(command, configuration);
		try {
			Context.pushCommandContext(commandContext);
			Context.pushConfiguration(configuration);
			return next.execute(command);
		} catch (Throwable t) {
			commandContext.exception(t);
		} finally {
			try {
				commandContext.close();
			} finally {
				Context.popCommandContext();
				Context.popConfiguration();
			}
		}
		return null;
	}
}
