package org.vivus.adb.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import org.vivus.adb.AdbException;

public class FxmlUtil {
	public static <T> T load(String resource) {
		try {
			return FXMLLoader.load(ResourceUtil.getResource(resource));
		} catch (IOException e) {
			throw new AdbException("can't load resource: " + resource);
		}
	}
}
