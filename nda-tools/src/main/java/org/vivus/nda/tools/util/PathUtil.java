package org.vivus.nda.tools.util;

public class PathUtil {
	public static final String FILE_SEPARATOR = "/";
	public static final char EXT_SEPARATOR = '.';

	private PathUtil() {
	}

	public static String getExtension(String path) {
		int lastIndex = path.lastIndexOf(EXT_SEPARATOR);
		return lastIndex < 0 ? "" : path.substring(lastIndex + 1).trim();
	}

	public static String getNameWithoutExt(String path) {
		String tempPath = path.replace("\\", FILE_SEPARATOR);
		int beginIndex = tempPath.lastIndexOf(FILE_SEPARATOR);
		int endIndex = tempPath.lastIndexOf(EXT_SEPARATOR);
		if (beginIndex < 0) {
			if (endIndex < 0) {
				return tempPath.trim();
			} else {
				return tempPath.substring(0, endIndex).trim();
			}
		} else {
			if (endIndex < 0) {
				return tempPath.substring(beginIndex + 1).trim();
			} else {
				return tempPath.substring(beginIndex + 1, endIndex).trim();
			}
		}
	}
}
