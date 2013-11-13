package org.vivus.adb;

public class Constants {
	// fxml
	public static final String FXML_BASE_DIR = "/fxml/";
	public static final String FXML_EXTENSION = ".fxml";
	public static final String FXML_MAIN = getFxmlFilePath("main");
	public static final String FXML_MAC = getFxmlFilePath("mac");

	public static String getFxmlFilePath(String name) {
		return FXML_BASE_DIR + name + FXML_EXTENSION;
	}

	// img
	public static final String IMG_BASE_DIR = "/img/";
	public static final String IMG_EXTENSION = ".png";
	public static final String IMG_ICON = "";

	public static String getImgFilePath(String name) {
		return IMG_BASE_DIR + name + IMG_EXTENSION;
	}
}
