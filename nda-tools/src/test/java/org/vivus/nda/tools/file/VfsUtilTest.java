package org.vivus.nda.tools.file;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class VfsUtilTest {
	private static final String BASE_FOLDER = ClassLoader.getSystemResource("").toString()
			+ "VfsUtilsTest/";

	@Test
	public void testAll() {
		String relativePath = BASE_FOLDER + "VfsUtilsTest.txt";
		VfsUtil.createFile(relativePath);
		VfsUtil.resolveFile(relativePath);
		Assert.assertTrue(VfsUtil.exist(relativePath));
		Assert.assertTrue(VfsUtil.isFolder(BASE_FOLDER));
		VfsUtil.delete(relativePath);
		VfsUtil.delete(BASE_FOLDER);
	}

	@Test
	public void testResolveFile() {
		String relativePath = BASE_FOLDER + "VfsUtilsTest.txt";
		FileObject relativeFile = VfsUtil.resolveFile(relativePath);
		System.out.println(relativeFile);
		String absolutePath = "D:/VfsUtilsTest/VfsUtilsTest.txt";
		FileObject absoluteFile = VfsUtil.resolveFile(absolutePath);
		System.out.println(absoluteFile);
	}

	// 打包后测试,检查是否在jar包内生成或操作相关文件
	// 测试拷贝文件到文件夹,拷贝后,文件夹被删除,替换为同名文件
	@Test
	public void testCopyFileToFolder() {
		String srcFile = BASE_FOLDER + "VfsUtilsTest.txt";
		String destFolder = BASE_FOLDER + "testCopyFileToFolder/";
		try {
			VfsUtil.createFile(srcFile);
			Assert.assertTrue(!VfsUtil.exist(destFolder));
			VfsUtil.mkdirs(destFolder);
			Assert.assertTrue(VfsUtil.exist(destFolder));
			Assert.assertTrue(VfsUtil.isFolder(destFolder));
			
			VfsUtil.copyFile(srcFile, destFolder, true);
			
			Assert.assertTrue(VfsUtil.exist(destFolder));
			Assert.assertTrue(VfsUtil.isFile(destFolder));
		} finally {
			VfsUtil.delete(srcFile);
			VfsUtil.delete(destFolder);
		}
	}

	// 测试拷贝文件夹到文件,拷贝后文件被删除,替换为同名文件夹
	@Test
	public void testCopyFolderToFile() {
		String srcFolder = BASE_FOLDER + "testCopyFileToFolder/";
		String srcFile = srcFolder + "VfsUtilsTest.txt";
		String destFile = BASE_FOLDER + "testCopyFileToFolder-copy.txt";
		try {
			VfsUtil.createFile(srcFile);
			Assert.assertTrue(VfsUtil.exist(srcFolder));
			Assert.assertTrue(VfsUtil.isFolder(srcFolder));
			VfsUtil.createFile(destFile);
			Assert.assertTrue(VfsUtil.exist(destFile));
			Assert.assertTrue(VfsUtil.isFile(destFile));
			
			VfsUtil.copyFile(srcFolder, destFile, true);
			
			Assert.assertTrue(VfsUtil.exist(destFile));
			Assert.assertTrue(VfsUtil.isFolder(destFile));
		} finally {
			VfsUtil.delete(srcFolder);
			VfsUtil.delete(destFile);
		}
	}

	// 测试文件拷贝
	@Test
	public void testCopyFile() {
		// 初始化
		String srcPath = BASE_FOLDER + "VfsUtilsTest.txt";
		String destPath = BASE_FOLDER + "VfsUtilsTest-copy.txt";
		VfsUtil.createFile(srcPath);
		VfsUtil.delete(destPath);
		Assert.assertTrue(!VfsUtil.exist(destPath));
		try {
			// 运行
			VfsUtil.copyFile(srcPath, destPath);
			// 验证
			Assert.assertTrue(VfsUtil.exist(destPath));
			Assert.assertTrue(VfsUtil.isFile(destPath));
			// test overwrite
			VfsUtil.copyFile(srcPath, destPath, true);
			// 验证
			Assert.assertTrue(VfsUtil.exist(destPath));
			Assert.assertTrue(VfsUtil.isFile(destPath));
		} finally {
			// 还原
			VfsUtil.delete(destPath);
			VfsUtil.delete(srcPath);
		}
	}

	// 测试文件夹拷贝
	@Test
	public void testCopyFolder() throws FileSystemException {
		String srcFolder = BASE_FOLDER;
		String destFolder = BASE_FOLDER + "VfsUtilsTest-copy/";
		String srcFile = srcFolder + "VfsUtilsTest.txt";
		VfsUtil.createFile(srcFile);
		Assert.assertTrue(!VfsUtil.exist(destFolder));
		try {
			VfsUtil.copyFile(srcFolder, destFolder, true);
			// 当目标存在时,不应将整个目录拷贝到目标目录下,而是拷贝子元素
			VfsUtil.copyFile(srcFolder, destFolder, true);
			VfsUtil.copyFile(srcFolder, destFolder, true);
			Assert.assertTrue(VfsUtil.exist(destFolder));
			Assert.assertTrue(VfsUtil.hasChildren(srcFolder));
			Assert.assertTrue(VfsUtil.hasChildren(destFolder));
			Assert.assertTrue(VfsUtil.resolveFile(srcFolder).getChildren().length == VfsUtil
					.resolveFile(destFolder).getChildren().length);
		} finally {
			VfsUtil.delete(srcFile);
			VfsUtil.delete(destFolder);
		}
	}

	// 测试文件夹拷贝,不覆盖,应抛错
	@Test(expected = FileException.class)
	public void testCopyFolderWithNotOverwrite() throws FileSystemException {
		String srcFolder = BASE_FOLDER;
		String destFolder = "VfsUtilsTest-copy/";
		String srcFile = srcFolder + "VfsUtilsTest.txt";
		VfsUtil.createFile(srcFile);
		VfsUtil.clear(destFolder);
		try {
			VfsUtil.copyFile(srcFolder, destFolder);
			VfsUtil.copyFile(srcFolder, destFolder);
		} finally {
			VfsUtil.delete(srcFile);
			VfsUtil.delete(destFolder);
		}
	}

	// 测试带子文件夹的文件夹拷贝
	@Test
	public void testCopyFolderWithSubfolder() {

	}

	// 测试存在同名文件时的文件拷贝
	@Test
	public void testCopyFileWithFileExist() {

	}

	// 测试存在同名文件夹时的文件拷贝
	@Test
	public void testCopyFileWithFolderExist() {

	}

	// 测试存在同名文件时的文件夹拷贝
	@Test
	public void testCopyFolderWithFileExist() {

	}

	// 测试存在同名文件夹时的文件夹拷贝
	@Test
	public void testCopyFolderWithFolderExist() {

	}

	@AfterClass
	public static void afterClassVfsUtilsTest() {
		VfsUtil.delete(BASE_FOLDER);
	}

}
