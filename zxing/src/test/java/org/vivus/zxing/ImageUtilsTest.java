package org.vivus.zxing;

import java.io.File;

import org.junit.Test;
import org.vivus.zxing.scan.ImageUtils;

public class ImageUtilsTest {
	public static final String FILE_SEPERATOR = "/";

	@Test
	public void testZoomIn() {
		String srcPath = "C:/Users/Administrator/Desktop/ul45211631-3.jpg";
		String newFileName = "ul45211631-3(in).jpg";
		String newPath = srcPath.substring(0, srcPath.lastIndexOf(FILE_SEPERATOR)) + FILE_SEPERATOR
				+ newFileName;
		ImageUtils.zoomInImage(srcPath, newPath, 2);
	}

	@Test
	public void testZoomOut() {
		String srcPath = "C:/Users/Administrator/Desktop/ul45211631-3.jpg";
		String newFileName = "ul45211631-3(out).jpg";
		String newPath = srcPath.substring(0, srcPath.lastIndexOf(FILE_SEPERATOR)) + FILE_SEPERATOR
				+ newFileName;
		ImageUtils.zoomOutImage(srcPath, newPath, 2);
	}

	@Test
	public void testFile() {
		System.out.println(File.pathSeparator);
		System.out.println(File.pathSeparatorChar);
		System.out.println(File.separator);
		System.out.println(File.separatorChar);
		System.out.println("".lastIndexOf("."));
		System.out.println("123".lastIndexOf("."));
		System.out.println("123.1".lastIndexOf("."));
		System.out.println("123.1.1.".lastIndexOf("."));
	}
}
