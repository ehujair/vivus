package org.vivus.nda.tools.ddmlib;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.IShellOutputReceiver;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;

public class AdbHelper {
	static final String BASE_DIR = "/sys/class/i2c-dev/i2c-2/device/2-0050/";
	static final String WIFI_FILE = BASE_DIR + "wifi_addr";
	static final String BLUETOOTH_FILE = BASE_DIR + "bt_addr";
	public static final int MIN = 0;
	public static final int MAX = 16777215;
	private static final Logger LOGGER = LoggerFactory.getLogger(AdbHelper.class);

	IMessageHandler messageHandler;
	static AndroidDebugBridge bridge;
	private IDevice device;

	public AdbHelper(IMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void writeMac(int mac) {
		writeMac(mac, "adb");
	}

	public void writeMac(int mac, String adbPath) {
		if (bridge == null) {
			AndroidDebugBridge.initIfNeeded(false);
			bridge = AndroidDebugBridge.createBridge(adbPath, false);
		}
		device = getDevice(bridge);
		writeWifi(device, mac);
		writeBluetooth(device, mac);
	}

	public void print(String message) {
		messageHandler.handle(message);
	}

	public void printNewMac() {
		IShellOutputReceiver bluetoothReceiver = new IShellOutputReceiver() {
			@Override
			public void addOutput(byte[] data, int offset, int length) {
				messageHandler.handle("新Mac地址：" + new String(data).substring(offset, length));
			}

			@Override
			public void flush() {
			}

			@Override
			public boolean isCancelled() {
				return false;
			}
		};
		executeShellCommand(device, bluetoothReceiver, "cat " + WIFI_FILE);
	}

	private void executeShellCommand(IDevice device, IShellOutputReceiver receiver, String command) {
		try {
			device.executeShellCommand(command, receiver);
		} catch (TimeoutException e) {
			LOGGER.error("execute shell comman '{}' error", command, e);
			throw new RuntimeException("execute shell comman '{}' error", e);
		} catch (AdbCommandRejectedException e) {
			LOGGER.error("execute shell comman '{}' error", command, e);
			throw new RuntimeException("execute shell comman '{}' error", e);
		} catch (ShellCommandUnresponsiveException e) {
			LOGGER.error("execute shell comman '{}' error", command, e);
			throw new RuntimeException("execute shell comman '{}' error", e);
		} catch (IOException e) {
			LOGGER.error("execute shell comman '{}' error", command, e);
			throw new RuntimeException("execute shell comman '{}' error", e);
		}
	}

	private void writeBluetooth(IDevice device, int mac) {
		check(mac);
		executeShellCommand(device, new EchoToFileShellOutputReceiver(), "echo " + mac + " > "
				+ BLUETOOTH_FILE);
	}

	private void check(int mac) {
		if (MAX < mac || mac < MIN) {
			throw new RuntimeException("mac should between " + MIN + " and " + MAX);
		}
	}

	private void writeWifi(IDevice device, int mac) {
		executeShellCommand(device, new EchoToFileShellOutputReceiver(), "echo " + mac + " > "
				+ WIFI_FILE);
	}

	class EchoToFileShellOutputReceiver implements IShellOutputReceiver {
		@Override
		public void addOutput(byte[] data, int offset, int length) {
			String message = new String(data).substring(offset, length);
			messageHandler.handle(message);
			throw new RuntimeException(message);
		}

		@Override
		public void flush() {
		}

		@Override
		public boolean isCancelled() {
			return false;
		}
	}

	private IDevice getDevice(AndroidDebugBridge bridge) {
		messageHandler.handle("等待设备连接...");
		IDevice[] devices = bridge.getDevices();
		try {
			while (devices == null || devices.length == 0) {
				Thread.sleep(500);
				devices = bridge.getDevices();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		messageHandler.handle("设备已连接");
		if (devices.length > 1) {
			throw new RuntimeException("检测到的设备多于1台: ");
		}
		IDevice device = devices[0];
		return device;
	}

	public static void main(String[] args) {
		new AdbHelper(new IMessageHandler() {
			@Override
			public void handle(String message) {
				System.out.println(message);
			}
		}).writeMac(100, "adb");
		AndroidDebugBridge.terminate();
	}
}
