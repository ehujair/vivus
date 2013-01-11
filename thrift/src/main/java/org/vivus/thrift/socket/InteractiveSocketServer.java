package org.vivus.thrift.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.vivus.thrift.socket.MultipleSocketServer.SocketRunner;

/*
 * 实现逻辑：保存起每个客户端的Socket，需要时即可广播或发送消息
 * TODO
 */
public class InteractiveSocketServer extends AbstractServer implements Server {

	public InteractiveSocketServer(int port) {
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
}
