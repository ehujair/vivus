package org.vivus.vfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;

public class VfsUtils {
	private static FileSystemManager fsm = null;

	static {
		try {
			fsm = VFS.getManager();
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

	public static FileObject getFile(String path) {
		try {
			return fsm.resolveFile(path);
		} catch (FileSystemException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void delete(String path) {
		try {
			FileObject fo = fsm.resolveFile(path);
			fo.delete();
		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isDirectory(String path) {
		try {
			FileObject fo = fsm.resolveFile(path);
			return fo.getType().equals(FileType.FOLDER);
		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static InputStream getInputStream(String path) {
		try {
			FileObject fo = fsm.resolveFile(path);
			return fo.getContent().getInputStream();
		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static OutputStream getOutputStream(String path) {
		try {
			FileObject fo = fsm.resolveFile(path);
			return fo.getContent().getOutputStream();
		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isFile(String path) {
		try {
			FileObject fo = fsm.resolveFile(path);
			return fo.getType().equals(FileType.FILE);
		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 函数描述：根据传入的文件路径创建文件夹(包括各级父文件夹)。如果路径中有文件，会自动去掉文件名。 （文件的判断是
	 * 以最后一个"/"之后是否有"."为标识的，）
	 * 
	 * @param path
	 * @return 如果创建成功，返回true；否则，返回false;
	 */
	public static boolean mkdirs(String path) {
		String realPath = "";
		path = path.replaceAll("\\\\", "/");
		// 如果该路径已"/"结尾，则整个字符串都是路径
		if (path.endsWith("/")) {
			realPath = path;
		} else {
			int fileNamePoint = path.lastIndexOf("/");
			// 获取真正的路径
			if (fileNamePoint >= 0) {
				realPath = path.substring(0, fileNamePoint);
			}
			// 读取文件名
			String fileName = path.substring(fileNamePoint + 1);
			// 如果读取的文件名中没有"."，说明整个字符串都是路径
			if (fileName.indexOf(".") < 0) {
				realPath = path;
			}
		}
		try {
			FileObject fo = fsm.resolveFile(realPath);
			fo.createFolder();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 函数描述：对文件进行copy
	 * 
	 * @param sourceFilePath
	 *            源文件的全部路径+文件名
	 * @param targetFilePath
	 *            目标文件的全部路径+文件名
	 * @param overWrite
	 *            如果目标文件存在，是否覆盖。true:覆盖；false:不覆盖(当源文件和目标文件都存在并且不覆盖时,返回true)。
	 * @return true:成功；false:失败; (当源文件和目标文件都存在并且不覆盖时,返回true)。
	 */
	public static boolean copyFile(String sourceFilePath, String targetFilePath, boolean overWrite)
			throws IOException {
		if (StringUtils.isBlank(sourceFilePath) || StringUtils.isBlank(targetFilePath)) {
			throw new IOException("源文件或者目标文件为空");
		}
		FileObject from = fsm.resolveFile(sourceFilePath);
		FileObject to = fsm.resolveFile(targetFilePath);
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

	/**
	 * Moving a File to Another File ,没有进行磁盘空间大小的判断
	 * 
	 * @param srcFile
	 *            源文件 eg: c:\windows\abc.txt
	 * @param targetFile
	 *            目标文件 eg: c:\temp\abc.txt
	 * @param overwrite
	 *            如果目标文件存在，是否覆盖
	 * @return success
	 */

	public static boolean moveFile(String srcFile, String targetFile, boolean overWrite)
			throws IOException {
		if (srcFile.equals(targetFile)) {
			return true;
		}
		FileObject src = fsm.resolveFile(srcFile);
		// File (or directory) to be moved
		if (StringUtils.isNotBlank(srcFile) && !src.exists()) {
			throw new IOException("源文件[" + srcFile + "]不存在");
		}
		// Destination directory
		FileObject to = fsm.resolveFile(targetFile);
		if (to.exists()) {
			if (to.getType() == FileType.FILE) {
				if (overWrite && !to.delete()) {
					throw new IOException("目标文件[" + targetFile + "]被保护，不能被覆盖！");
				} else if (!overWrite) {
					throw new IOException("目标文件[" + targetFile + "]已经存在！");
				}
			}
		}
		src.moveTo(to);
		return true;
	}
}
