package org.vivus.vfs;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class VfsTest {
	static final String SRC_FILE = "jar:/D:/m2/repository/org/apache/commons/commons-vfs2/2.0/commons-vfs2-2.0.jar";
	static final String DEST_FILE = "jar:/D:/commons-vfs2-2.0.jar";
	static final String LOCAL_SRC_FILE = "D:/m2/repository/org/apache/commons/commons-vfs2/2.0/commons-vfs2-2.0.jar";
	static final String LOCAL_DEST_FILE = "D:/commons-vfs2-2.0.jar";

	static FileSystemManager fsManager;

	@BeforeClass
	public static void vfsTestBeforeClass() throws FileSystemException {
		fsManager = VFS.getManager();
	}

	@Test
	public void testResolveFile() throws FileSystemException {
		FileObject jarFile = fsManager.resolveFile(SRC_FILE);
		System.out.println("jarFile: " + jarFile.getName().getURI());
	}

	@Test
	public void testCreateFile() throws FileSystemException {
		FileObject resolveFile = fsManager.resolveFile(DEST_FILE);
		System.out.println("" + resolveFile.getName().getURI());
	}

	@Test
	public void testCopyFile() throws IOException {
		copyFile(LOCAL_SRC_FILE, LOCAL_DEST_FILE, true);
	}

	@AfterClass
	public static void vfsTestAfterClass() {
		fsManager = null;
	}

	public static boolean copyFile(String sourceFilePath, String targetFilePath, boolean overWrite)
			throws IOException {
		if (StringUtils.isBlank(sourceFilePath) || StringUtils.isBlank(targetFilePath)) {
			throw new IOException("源文件或者目标文件为空");
		}
		FileObject from = fsManager.resolveFile(sourceFilePath);
		FileObject to = fsManager.resolveFile(targetFilePath);
		if (to.exists()) {
			if (to.getType() == FileType.FILE) {
				if (overWrite && !to.delete()) {
					throw new IOException("目标文件[" + targetFilePath + "]被保护，不能被覆盖！");
				} else if (!overWrite) {
					throw new IOException("目标文件[" + targetFilePath + "]已经存在！");
				}
			}
		}
		to.copyFrom(from, Selectors.SELECT_ALL);
		return true;
	}
}
