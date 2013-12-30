package org.vivus.nda.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceUtil {
	private ResourceUtil() {
	}

	public static URL getResource(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
		URL url = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			url = classLoader.getResource(stripped);
		}
		if (url == null) {
			url = ResourceUtil.class.getResource(resource);
		}
		if (url == null) {
			url = ResourceUtil.class.getClassLoader().getResource(stripped);
		}
		return url;
	}

	public static InputStream getResourceAsStream(String resource) {
		URL url = getResource(resource);
		try {
			return url != null ? url.openStream() : null;
		} catch (IOException e) {
			return null;
		}
	}
}
