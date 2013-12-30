package org.vivus.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebAppContextServer {
	public static void main(String[] args) {
		Server server = new Server(8080);

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { new WebAppContext("src/main/webapp", "/jetty"),
				new DefaultHandler() });
		server.setHandler(handlers);

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
