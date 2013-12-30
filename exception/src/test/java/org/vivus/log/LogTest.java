package org.vivus.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	static final Logger logger = LoggerFactory.getLogger(LogTest.class);

	@Test
	public void test() {
		logger.trace("test trace log");
		logger.debug("test debug log");
		logger.info("test info log");
		logger.warn("test warn log");
		logger.error("test error log");
	}
}
