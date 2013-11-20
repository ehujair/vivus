package org.vivus.fileupload;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.vivus.fileupload.servlet.FileSelectionServlet;
import org.vivus.fileupload.servlet.FileServlet;
import org.vivus.nda.tools.IEngine;
import org.vivus.nda.tools.config.Configuration;

public class FileUploadServer {
	protected static IEngine engine = new Configuration().buildEngine();

	public static IEngine getEngine() {
		return engine;
	}

	public static void main(String[] args) {
		Server server = new Server();

		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });

		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/FileUpload");
		context.addServlet(FileSelectionServlet.class, "/fileSelection");
		context.addServlet(FileServlet.class, "/file");

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
		server.setHandler(handlers);

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
