package org.vivus.thrift.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleSocketServer extends AbstractServer implements Server {
	static Logger logger = LoggerFactory.getLogger(MultipleSocketServer.class);

	public MultipleSocketServer(int port) {
		super(port);
	}

	@Override
	protected void start_() {
		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(port);
			while (!stoped) {
				socket = ss.accept();
				new SocketRunner(socket);
			}
		} catch (IOException e) {
			logger.error("start server error...", e);
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
				if (ss != null) {
					ss.close();
				}
			} catch (IOException e) {
				logger.error("error when closing ...", e);
			}
		}
	}

	static class SocketRunner implements Runnable {
		Socket socket;

		SocketRunner(Socket socket) {
			this.socket = socket;
			Thread thread = new Thread(this);
			thread.start();
		}

		@Override
		public void run() {
			InputStream inputStream = null;
			OutputStream outputStream = null;
			try {
				inputStream = socket.getInputStream();
				byte[] r = new byte[2];
				int rNum = inputStream.read(r);
				logger.info("server read {} byte: {}", rNum, r);
				outputStream = socket.getOutputStream();
				byte[] b = { 2, 2 };
				logger.info("server write: {}", b);
				outputStream.write(b);
			} catch (IOException e) {
				logger.error("start server error...", e);
			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					logger.error("error when closing ...", e);
				}
			}
		}

	}
}
