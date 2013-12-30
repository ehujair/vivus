package org.vivus.comm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialReader {
	static final Logger logger = LoggerFactory.getLogger(SerialReader.class); 
	int rate = 9600;
	int dataBit = SerialPort.DATABITS_8;
	int stopBit = SerialPort.STOPBITS_1;
	int parity = SerialPort.PARITY_NONE;

	public void open(String portName) throws NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException, TooManyListenersException, IOException {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			logger.error("Error: Port is currently in use");
		} else {
			CommPort commPort = portIdentifier.open(this.getClass().getName(), 1000);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(rate, dataBit, stopBit, parity);

				 InputStream in = serialPort.getInputStream();
				// OutputStream out = serialPort.getOutputStream();

				// (new Thread(new SerialWriter(out))).start();

				serialPort.addEventListener(new SerialReaderListener(in));
				serialPort.notifyOnDataAvailable(true);
			} else {
				logger.error("Error: Only serial ports are handled.");
			}
		}
	}

	public static class SerialReaderListener implements SerialPortEventListener {
		private InputStream in;
		private byte[] buffer = new byte[1024];

		public SerialReaderListener(InputStream in) {
			this.in = in;
		}

		public void serialEvent(SerialPortEvent event) {
			int len = 0;
			try {
				len = read(buffer, in);
			} catch (IOException e) {
				logger.error("Error: Read serial ports error", e);
			}
			System.out.print(new String(buffer, 0, len));
		}

	}

	public static int read(byte[] buffer, InputStream in) throws IOException {
		int data;
		int len = 0;
		while ((data = in.read()) > -1) {
			if (data == '\n') {
				break;
			}
			buffer[len++] = (byte) data;
		}
		return len;
	}

	public static void main(String[] args) {
		try {
			String portName = "COM1";
			if (args.length > 0) {
				portName = args[0];
			}
			new SerialReader().open(portName);
		} catch (Exception e) {
			logger.error("Error: open SerialReader error", e);
			System.exit(-1);
		}
	}
}
