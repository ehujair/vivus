package org.vivus.nda.tools.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInterceptor extends ACommandInterceptor {
	private static Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public <T> void before(ICommand<T> command) {
		LOGGER.info("----- starting {} -----", command.getClass().getName());
	}

	@Override
	public <T> void after(ICommand<T> command) {
		LOGGER.info("----- {} finished -----", command.getClass().getName());
	}
}
