package org.vivus.javafx.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static final String EXTENSION_SEPERATOR = ",";
	public static String EXCEL = "Excel";
	public static String EXCEL_EXT = ".xlsx";
	public static String EXCEL_97TO2003 = "Excel 97-2003";
	public static String EXCEL_97TO2003_EXT = ".xls";

	private ExcelUtil() {
	}

	public static void upload(WorkbookHandler workbookHandler, Window ownerWindow, String title,
			String extensionDescription, String... extensions) throws InvalidFormatException,
			IOException {
		File selectedFile = chooseFile(ownerWindow, title, extensionDescription, extensions);
		if (selectedFile != null) {
			handleFile(workbookHandler, selectedFile);
		}
	}

	private static File chooseFile(Window ownerWindow, String title, String extensionDescription,
			String... extensions) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		addExtensionFilter(fileChooser, extensionDescription, extensions);
		return fileChooser.showOpenDialog(ownerWindow);
	}

	private static void addExtensionFilter(FileChooser fileChooser, String extensionDescription,
			String... extensions) {
		if (extensions == null || extensions.length == 0) {
			extensions = new String[] { "*.*" };
			if (extensionDescription == null || "".equals(extensionDescription.trim())) {
				extensionDescription = "all files";
			}
		}
		addExtensionFilter(
				fileChooser,
				new ExtensionFilter(spliceDescription(extensionDescription, extensions), extensions));
	}

	private static void addExtensionFilter(FileChooser fileChooser,
			ExtensionFilter... extensionFilters) {
		fileChooser.getExtensionFilters().addAll(extensionFilters);
	}

	public static String spliceDescription(String extensionDescription, String... extensions) {
		StringBuffer sb = new StringBuffer();
		for (String extension : extensions) {
			sb.append(extension + EXTENSION_SEPERATOR);
		}
		String extensionString = sb.substring(0, sb.lastIndexOf(EXTENSION_SEPERATOR));
		return extensionDescription + "(" + extensionString + ")";
	}

	private static void handleFile(WorkbookHandler workbookHandler, File selectedFile)
			throws IOException, InvalidFormatException, FileNotFoundException {
		if (!workbookHandler.accept(getExtension(selectedFile))) {
			throw new FileException(selectedFile, "The " + selectedFile.getName()
					+ " file is not acceptable by " + workbookHandler.getClass().getName());
		}
		workbookHandler.handle(WorkbookFactory.create(new FileInputStream(selectedFile)));
	}

	public static <T> void download(DataHandler<T> dataHandler, T data, Window ownerWindow,
			String title, String fileName, ExtensionFilter... extensionFilters)
			throws InvalidFormatException, IOException {
		// choose directory
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setInitialFileName(fileName);
		addExtensionFilter(fileChooser, extensionFilters);
		File file = fileChooser.showSaveDialog(ownerWindow);
		if (file != null) {
			if (!file.exists()) {
				if (!file.createNewFile()) {
					throw new FileException(file, "Can not create file: " + file.getPath());
				}
			}
			// data convert to workbook
			Workbook workbook = createWorkbook(file);
			dataHandler.handle(workbook, data);
			// save file
			FileOutputStream fileOut = new FileOutputStream(file);
			try {
				workbook.write(fileOut);
			} finally {
				fileOut.close();
			}
		}
	}

	public static Workbook createWorkbook(File file) {
		return createWorkbook(getExtension(file));
	}

	public static Workbook createWorkbook(String extension) {
		if (EXCEL_EXT.equals(extension)) {
			return new XSSFWorkbook();
		} else if (EXCEL_97TO2003_EXT.equals(extension)) {
			return new HSSFWorkbook();
		}
		throw new IllegalArgumentException("not supported file type: " + extension);
	}

	public static String getExtension(File file) {
		return getExtension(file.getPath());
	}

	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
