package org.vivus.nda.tools.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.FileUtil;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扩展{@link FileUtil}的工具类
 * 
 * @author Administrator
 * @see FileUtil
 */
public class VfsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(VfsUtil.class);
	private static final int BUFFER_SIZE = 1024;

	private static FileSystemManager fsm;

	static {
		try {
			fsm = VFS.getManager();
		} catch (FileSystemException e) {
			LOGGER.error("", e);
		}
	}

	private VfsUtil() {
	}

	public static FileObject resolveFile(String path) {
		try {
			return fsm.resolveFile(path);
		} catch (FileSystemException e) {
			throw new FileException("resolve file exception", e);
		}
	}

	public static long getSize(FileObject file) {
		try {
			return file.getContent().getSize();
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	public static void writeContent(InputStream inputStream, String destFile) {
		FileObject target = resolveFile(destFile);
		writeContent(inputStream, target);
	}

	public static void writeContent(InputStream inputStream, FileObject target) {
		OutputStream outputStream = null;
		try {
			outputStream = target.getContent().getOutputStream();
			writeContent(inputStream, outputStream);
		} catch (FileSystemException e) {
			throw new FileException("", e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					LOGGER.error("", e);
					// ignore me;
				}
			}
		}
	}

	public static void writeContent(String source, OutputStream outputStream) {
		InputStream inputStream = null;
		try {
			inputStream = resolveFile(source).getContent().getInputStream();
			writeContent(inputStream, outputStream);
		} catch (FileSystemException e) {
			throw new FileException("", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.error("", e);
					// ignore me;
				}
			}
		}
	}

	public static void writeContent(InputStream inputStream, OutputStream outputStream) {
		try {
			final byte[] buffer = new byte[BUFFER_SIZE];
			while (true) {
				final int nread = inputStream.read(buffer);
				if (nread < 0) {
					break;
				}
				outputStream.write(buffer, 0, nread);
			}
		} catch (IOException e) {
			throw new FileException("", e);
		}
	}

	public static FileObject createFile(String path) {
		try {
			FileObject file = resolveFile(path);
			file.createFile();
			return file;
		} catch (FileSystemException e) {
			throw new FileException("create file exception", e);
		}
	}

	public static void delete(String path) {
		try {
			resolveFile(path).delete(Selectors.SELECT_ALL);
		} catch (FileSystemException e) {
			throw new FileException("delete file exception", e);
		}
	}

	public static boolean exist(String path) {
		try {
			return resolveFile(path).exists();
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	// the code under this line is to be deleted

	public static void clear(String path) {
		try {
			resolveFile(path).delete(Selectors.EXCLUDE_SELF);
		} catch (FileSystemException e) {
			throw new FileException("delete file exception", e);
		}
	}

	public static boolean hasChildren(String path) {
		try {
			FileObject[] children = resolveFile(path).getChildren();
			return (children == null || children.length == 0) ? false : true;
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	public static boolean isFolder(String path) {
		try {
			return resolveFile(path).getType().equals(FileType.FOLDER);
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	public static InputStream getInputStream(String path) {
		try {
			return resolveFile(path).getContent().getInputStream();
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	public static OutputStream getOutputStream(String path) {
		try {
			return resolveFile(path).getContent().getOutputStream();
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	public static boolean isFile(String path) {
		try {
			return resolveFile(path).getType().equals(FileType.FILE);
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

	/**
	 * 函数描述：根据传入的文件路径创建文件夹(包括各级父文件夹)。
	 * 
	 * @param path
	 */
	public static void mkdirs(String path) {
		try {
			resolveFile(path).createFolder();
		} catch (Exception e) {
			throw new FileException("", e);
		}
	}

	public static void copyFile(String sourceFilePath, String targetFilePath) {
		copyFile(sourceFilePath, targetFilePath, false);
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
	 */
	public static void copyFile(String sourceFilePath, String targetFilePath, boolean overWrite) {
		try {
			FileObject src = resolveFile(sourceFilePath);
			FileObject target = resolveFile(targetFilePath);
			checkFile(target, overWrite);
			target.copyFrom(src, Selectors.SELECT_ALL);
		} catch (FileSystemException e) {
			throw new FileException("can not copy file " + sourceFilePath, e);
		}
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
	public static void moveFile(String srcFile, String targetFile, boolean overWrite) {
		if (StringUtils.isNotBlank(srcFile)) {
			throw new IllegalArgumentException("argument srcFile can not be blank");
		}
		if (srcFile.equals(targetFile)) {
			return;
		}
		try {
			FileObject from = resolveFile(srcFile);
			FileObject to = resolveFile(targetFile);
			checkFile(to, overWrite);
			from.moveTo(to);
		} catch (FileSystemException e) {
			throw new FileException("can not move file " + srcFile, e);
		}
	}

	private static void checkFile(FileObject target, boolean overWrite) {
		try {
			if (target.exists()) {
				if (!overWrite) {
					throw new FileException("file[" + target.getName().getURI() + "] already exist");
				}
			}
		} catch (FileSystemException e) {
			throw new FileException("", e);
		}
	}

}
