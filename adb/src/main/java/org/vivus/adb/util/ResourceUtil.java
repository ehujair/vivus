package org.vivus.adb.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.vivus.adb.AdbException;

public class ResourceUtil {

	public static URL getResource(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
		URL url = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			url = classLoader.getResource(stripped);
		}
		if (url == null) {
			url = FxmlUtil.class.getResource(resource);
		}
		if (url == null) {
			url = FxmlUtil.class.getClassLoader().getResource(stripped);
		}
		if (url == null) {
			throw new AdbException(resource + " not found");
		}
		return url;
	}

	public static InputStream getResourceAsStream(String resource) {
		try {
			return getResource(resource).openStream();
		} catch (IOException e) {
			throw new AdbException("", e);
		}
	}
}
