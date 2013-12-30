package org.vivus.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.vivus.adb.impl.MacAddressManager;

public class MacHelper {
	static final String BASE_DIR = "/sys/class/i2c-dev/i2c-2/device/2-0050/";
	static final String WIFI_FILE = "wifi_addr";
	static final String BLUETOOTH_FILE = "bt_addr";
	AdbEngine adbEngine = new Configuration().build();
	AdbHandler adbHandler;

	public MacHelper(AdbHandler adbHandler) {
		this.adbHandler = adbHandler;
	}

	// public static void main(String[] args) {
	// writeMac();
	// }

	public void writeMac() {
		try {
			// wait-for-device:
			adbHandler.handleMessage("等待设备连接...");
			Process waitProcess = Runtime.getRuntime().exec("adb wait-for-device");
			int waitFor = waitProcess.waitFor();
			if (waitFor == 0) {
				adbHandler.handleMessage("设备已连接");
			} else {
				adbHandler.handleMessage("设备连接出错,请检查后重试");
				return;
			}
			// 启动adb shell
			final Process adbShellProcess = Runtime.getRuntime().exec("adb shell");
			// 启动输入监听
			new Thread(new Runnable() {
				public void run() {
					InputStream inputStream = adbShellProcess.getInputStream();
					BufferedReader inputReader = new BufferedReader(new InputStreamReader(
							inputStream));
					System.out.println("input listener started");
					try {
						String line;
						while ((line = inputReader.readLine()) != null) {
							adbHandler.handleInput(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			// 启动错误监听
			new Thread(new Runnable() {
				public void run() {
					InputStream errorStream = adbShellProcess.getErrorStream();
					BufferedReader errorReader = new BufferedReader(new InputStreamReader(
							errorStream));
					System.out.println("error listener started");
					try {
						boolean fail = false;
						String line;
						while ((line = errorReader.readLine()) != null) {
							fail = true;
							adbHandler.handleError(line);
							System.out.println("fail in: " + fail);
						}
						System.out.println("fail out: " + fail);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			// 取新Mac地址
			String mac = adbEngine.getMacGenerator().generateMac();
			OutputStream outputStream = adbShellProcess.getOutputStream();
			// 写wifi地址
			outputStream.write(("echo " + mac + " > " + BASE_DIR + WIFI_FILE + System
					.lineSeparator()).getBytes());
			outputStream.flush();
			// 写bluetooth地址
			outputStream.write(("echo " + mac + " > " + BASE_DIR + BLUETOOTH_FILE + System
					.lineSeparator()).getBytes());
			outputStream.flush();
			// 打印新Mac地址
			outputStream.write(("cat " + BASE_DIR + WIFI_FILE + System.lineSeparator()).getBytes());
			outputStream.flush();
			outputStream.write(("cat " + BASE_DIR + BLUETOOTH_FILE + System.lineSeparator())
					.getBytes());
			outputStream.flush();
			// 退出
			outputStream.write(("exit" + System.lineSeparator()).getBytes());
			outputStream.flush();
			// 写入数据库
			saveMac(mac);
			// 打印成功信息
			adbHandler.handleEnded(adbShellProcess.waitFor());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void saveMac(String mac) {
		SqlSession session = adbEngine.getSqlSessionFactory().openSession();
		MacAddressManager macAddressManager = new MacAddressManager(session);
		try {
			MacAddress macAddress = new MacAddress();
			macAddress.setMac(mac);
			macAddress.setWriteTime(new Date());
			macAddressManager.insert(macAddress);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw new AdbException("can not insert MacAddress", e);
		} finally {
			session.close();
		}
	}
}
