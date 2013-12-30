package org.vivus.nda.tools.util;


import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class PathUtilTest {
	@Test
	public void testGetExtension() {
		Assert.assertEquals("txt", PathUtil.getExtension("D:/test/tt.txt"));
		Assert.assertEquals("txt", PathUtil.getExtension("tt.txt"));
		Assert.assertEquals("", PathUtil.getExtension(""));
		Assert.assertEquals("", PathUtil.getExtension("D:/test/"));
		Assert.assertEquals("txt", PathUtil.getExtension(".txt"));
		Assert.assertEquals("", PathUtil.getExtension("."));
		Assert.assertEquals("", PathUtil.getExtension(".  "));
		Assert.assertEquals("", PathUtil.getExtension("tt."));
	}
	@Test
	public void testGetNameWithoutExt() {
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("D:/test/tt.txt"));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("D:\\test\\tt.txt"));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("D:/test/tt."));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("D:\\test\\tt."));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("D:/test/tt"));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("D:\\test\\tt"));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("tt.txt"));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("tt"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(""));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(" \r  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(" \n  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(" \r\n  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(System.lineSeparator()));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("D:/test/"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("D:\\test\\"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(".txt"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("."));
		Assert.assertEquals("", PathUtil.getNameWithoutExt(".  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  .  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  /.  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  \\.  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("/"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("\\"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("/  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("\\  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  /  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  \\  "));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  /"));
		Assert.assertEquals("", PathUtil.getNameWithoutExt("  \\"));
		Assert.assertEquals("tt", PathUtil.getNameWithoutExt("tt."));
	}

	@Test
	public void testSeperator() {
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
	}

	@Test
	public void testIndexOfFileSeperator() {
		String contact = ": ";
		String str1 = "";
		String str2 = "D:/test/tt.txt";
		String str3 = "D:\\test\\tt.txt";
		System.out.println(str1 + contact + str1.indexOf(File.separator));
		System.out.println(str2 + contact + str2.indexOf(File.separator));
		System.out.println(str3 + contact + str3.indexOf(File.separator));
		String root = ClassLoader.getSystemResource(str1).toString();
		System.out.println(root + contact + root.indexOf(File.separator));
	}

}
