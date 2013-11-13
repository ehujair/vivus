package org.vivus.adb;

public interface AdbHandler {
	void handleMessage(String msg);

	void handleInput(String input);

	void handleError(String error);

	void handleEnded(int exitValue);
}
