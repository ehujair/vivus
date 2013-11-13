package org.vivus.nda.tools.file;

public class FileException extends RuntimeException {
	private static final long serialVersionUID = 7615614967801290628L;

	public FileException(String message) {
		super(message);
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}
}
