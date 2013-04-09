package org.vivus.javafx.file;

import java.io.File;

public class FileException extends RuntimeException {
	private static final long serialVersionUID = 4546026677359575163L;

	File file;

	public FileException(File file, String message) {
		super(message);
		this.file = file;
	}

	public FileException(File file, String message, Throwable cause) {
		super(message, cause);
		this.file = file;
	}

	public File getFile() {
		return file;
	}
}
