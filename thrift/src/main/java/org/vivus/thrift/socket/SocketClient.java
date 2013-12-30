package org.vivus.thrift.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketClient implements Client {
	static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

	String host;
	InetAddress address;
	int port;

	public SocketClient(String host, int port) {
		this.host = host;
		try {
			this.address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			logger.error("Unknown host: {}", host, e);
			this.address = null;
		}
		this.port = port;
	}

	@Override
	public void invoke() {
		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			socket = new Socket(address, port);
			byte[] b = { 1, 1 };
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				outputStream = socket.getOutputStream();
				logger.info("client write: {}", b);
				outputStream.write(b);
				outputStream.flush();
				inputStream = socket.getInputStream();
				byte[] r = new byte[2];
				int rNum = inputStream.read(r);
				logger.info("client read {} byte: {}", rNum, r);
			}
		} catch (IOException e) {
			logger.error("Client invoke error...", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				logger.error("error when closing ...", e);
			}
		}
	}

	public static void main(String[] args) {
		Client socketClient = new SocketClient("localhost", 8000);
		socketClient.invoke();
	}
}
