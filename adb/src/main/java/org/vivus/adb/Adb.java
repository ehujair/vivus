package org.vivus.adb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Adb {
	public static void main(String[] args) {
		adbShellCat();
	}

	public static void adbShellCat() {
		try {
//			Process catProcess = Runtime.getRuntime().exec(
//					"adb shell cat " + MacHelper.BASE_DIR + MacHelper.WIFI_FILE);
			Process catProcess = Runtime.getRuntime().exec("cmd");
			OutputStream outputStream = catProcess.getOutputStream();
			outputStream.write(("adb shell cat " + MacHelper.BASE_DIR + MacHelper.WIFI_FILE + "\r\n").getBytes());
			outputStream.flush();
			InputStream inputStream = catProcess.getInputStream();
			StringBuffer sb = new StringBuffer();
			byte[] inputCache = new byte[1024];
			while (inputStream.read(inputCache) != -1) {
				sb.append(inputCache);
			}
			System.out.println("写入Mac地址成功,新Mac地址为: " + sb.toString());
			catProcess.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void cmd2() {
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe");
//			Process process = Runtime.getRuntime().exec("adb shell");
			
			InputStream inputStream = process.getInputStream();
			byte[] inputCache = new byte[1024];
			while (inputStream.read(inputCache) != -1) {
				System.out.println(new String(inputCache));
			}
			
			InputStream errStream = process.getInputStream();
			byte[] errCache = new byte[1024];
			while (errStream.read(errCache) != -1) {
				System.err.println(new String(errCache));
			}
			
			OutputStream outputStream = process.getOutputStream();
			PrintWriter outputWriter = new PrintWriter(outputStream, true);
			outputWriter.print("dir\n");
//			outputWriter.print("ls\n");
			
			int i = process.waitFor();
			System.out.println("i=" + i);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void openFile() {
		try {
			Runtime.getRuntime().exec("cmd /c start ./src/main/java/org/vivus/adb/test.txt");
//			Runtime.getRuntime().exec("notepad.exe src/main/java/org/vivus/adb/test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exec() {
		Process process;
		try {
			process = Runtime.getRuntime().exec("adb version");
			InputStream inputStream = process.getInputStream();
			byte[] cache = new byte[1024];
			while (inputStream.read(cache) != -1) {
				System.out.println(new String(cache));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cmd() {
		try {
			Process process = Runtime.getRuntime().exec("cmd /c dir");
			final InputStream inputStream = process.getInputStream();
			new Thread(new Runnable() {
				byte[] cache = new byte[1024];
				
				public void run() {
					System.out.println("listener started");
					try {
						while (inputStream.read(cache) != -1) {
							System.out.println(new String(cache));
						}
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}).start();
//			OutputStream outputStream = process.getOutputStream();
//			outputStream.write(new byte[]{'e', 'x', 'i', 't', '\n'});
			// inputStream.
			int i = process.waitFor();
			System.out.println("i=" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cmdNotClose() {
		try {
			Process process = Runtime.getRuntime().exec("adb shell");
			final InputStream inputStream = process.getInputStream();
			new Thread(new Runnable() {
				byte[] cache = new byte[1024];

				public void run() {
					System.out.println("listener started");
					try {
						while (inputStream.read(cache) != -1) {
							System.out.println(new String(cache));
						}
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}).start();
			OutputStream outputStream = process.getOutputStream();
			PrintWriter outputWriter = new PrintWriter(outputStream, true);
			outputWriter.print("ls\n");
			// inputStream.
			int i = process.waitFor();
			System.out.println("i=" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void adbShell() {
		try {
			Process process = Runtime.getRuntime().exec("cmd");
//			Process process = Runtime.getRuntime().exec("adb shell");
			final BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(
					process.getOutputStream()));
			final BufferedReader inputStream = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			// 这里一定要注意错误流的读取，不然很容易阻塞，得不到你想要的结果，
			final BufferedReader errorReader = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			new Thread(new Runnable() {
				String line;

				public void run() {
					System.out.println("input listener started");
					try {
						while ((line = inputStream.readLine()) != null) {
							System.out.println(line);
						}
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				String line;

				public void run() {
					System.out.println("error listener started");
					try {
						while ((line = errorReader.readLine()) != null) {
							System.out.println(line);
						}
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}).start();
//			new Thread(new Runnable() {
//				final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//				public void run() {
//					System.out.println("writer started");
//					String line;
//					try {
//						while ((line = br.readLine()) != null) {
//							outputStream.write(line + "\r\n");
//							outputStream.flush();
//						}
//					} catch (IOException e) {
//						// e.printStackTrace();
//					}
//				}
//			}).start();
			outputStream.write("dir" + "\r\n");
			outputStream.flush();
			outputStream.write("exit" + "\r\n");
			outputStream.flush();
			int i = process.waitFor();
			System.out.println("i=" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}