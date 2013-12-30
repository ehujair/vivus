package adbexplorer.util;

public class Log4jInit {

	public Log4jInit() {
		java.net.URL url = Log4jInit.class.getResource("/lib/log4j.cfg");
		org.apache.log4j.PropertyConfigurator.configure(url);
	}

	
}