package org.vivus.adb;

public class AdbException extends RuntimeException {
	private static final long serialVersionUID = 5542389577751187799L;

	public AdbException(String message) {
		super(message);
	}

	public AdbException(String message, Throwable cause) {
		super(message, cause);
	}
}
