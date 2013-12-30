package org.vivus.nda.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInterceptor extends CommandInterceptor {
	private static Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public <T> void before(Command<T> command) {
		LOGGER.info("----- starting {} -----", command.getClass().getName());
	}

	@Override
	public <T> void after(Command<T> command) {
		LOGGER.info("----- {} finished -----", command.getClass().getName());
	}
}
