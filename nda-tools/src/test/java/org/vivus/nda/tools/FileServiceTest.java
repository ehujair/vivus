package org.vivus.nda.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.file.IFileQuery;
import org.vivus.nda.tools.file.IPathResolver;
import org.vivus.nda.tools.file.VfsUtil;
import org.vivus.nda.tools.impl.Engine;

public class FileServiceTest extends NdaToolsTest {
	private static final String BASE_FOLDER = ClassLoader.getSystemResource("").toString()
			+ "FileServiceTest/";
	private IFileService fileService = engine.getFileService();

	// 上传文件
	@Test
	public void testUploadLocalFile() throws FileSystemException {
		FileObject source = VfsUtil.createFile(BASE_FOLDER + "testUploadLocalFile.txt");
		InputStream inputStream = source.getContent().getInputStream();
		try {
			FileItem fileItem = fileService.upload(source.getName().getBaseName(), inputStream);
			// validate
			Assert.assertNotNull(fileItem);
			Assert.assertTrue(StringUtils.isNotBlank(fileItem.getId()));
			// 判断文件是否已真上传
			IPathResolver pathResolver = ((Engine) engine).getConfiguration().getPathResolver();
			Assert.assertTrue(VfsUtil.exist(pathResolver.getBasePath() + fileItem.getPath()));
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					// ignore me;
				}
			}
		}
	}

	// 下载文件
	@Test
	public void testDownloadFile() throws FileSystemException {
		String uploadFileName = "testDownloadFile-upload.txt";
		FileObject source = VfsUtil.createFile(BASE_FOLDER + uploadFileName);
		InputStream inputStream = source.getContent().getInputStream();
		String target = BASE_FOLDER + "testDownloadFile-download.txt";
		OutputStream outputStream = VfsUtil.resolveFile(target).getContent().getOutputStream();
		try {
			FileItem fileItem = fileService.upload(source.getName().getBaseName(), inputStream);
			Assert.assertNotNull(fileItem);
			Assert.assertTrue(StringUtils.isNotBlank(fileItem.getId()));
			FileItem download = fileService.download(fileItem.getId(), outputStream);
			Assert.assertNotNull(download);
			Assert.assertTrue(StringUtils.isNotBlank(download.getId()));
			Assert.assertEquals(uploadFileName, download.getName());
			Assert.assertTrue(VfsUtil.exist(target));
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					// ignore me;
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					// ignore me;
				}
			}
		}
	}

	// 删除文件
	@Test
	public void testDeleteFile() throws FileSystemException {
		String uploadFileName = "testDeleteFile-upload.txt";
		FileObject source = VfsUtil.createFile(BASE_FOLDER + uploadFileName);
		InputStream inputStream = source.getContent().getInputStream();
		try {
			FileItem fileItem = fileService.upload(source.getName().getBaseName(), inputStream);
			Assert.assertNotNull(fileItem);
			Assert.assertTrue(StringUtils.isNotBlank(fileItem.getId()));
			IPathResolver pathResolver = ((Engine) engine).getConfiguration().getPathResolver();
			Assert.assertTrue(VfsUtil.exist(pathResolver.getBasePath() + fileItem.getPath()));

			fileService.delete(fileItem.getId());
			FileItem file = fileService.load(fileItem.getId());
			Assert.assertNull(file);
			Assert.assertFalse(VfsUtil.exist(pathResolver.getBasePath() + fileItem.getPath()));
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					// ignore me;
				}
			}
		}
	}

	// 查询文件列表
	@Test
	public void testQueryFiles() {
		IFileQuery fileQuery = fileService.createFileQuery();
		System.out.println(fileQuery.count());
		fileQuery.nameLike("%test%");
		fileQuery.sizeBetween(1, 102400);
		fileQuery.orderByName();
		List<FileItem> list = fileQuery.list();
		System.out.println(list);
		System.out.println(list.size());
	}

	// 获取指定文件信息
	@Test
	public void testLoadFile() throws FileSystemException {
		String uploadFileName = "testDeleteFile-upload.txt";
		FileObject source = VfsUtil.createFile(BASE_FOLDER + uploadFileName);
		InputStream inputStream = source.getContent().getInputStream();
		try {
			FileItem fileItem = fileService.upload(source.getName().getBaseName(), inputStream);
			Assert.assertNotNull(fileItem);
			Assert.assertTrue(StringUtils.isNotBlank(fileItem.getId()));
			IPathResolver pathResolver = ((Engine) engine).getConfiguration().getPathResolver();
			Assert.assertTrue(VfsUtil.exist(pathResolver.getBasePath() + fileItem.getPath()));

			FileItem file = fileService.load(fileItem.getId());
			Assert.assertNotNull(file);
			Assert.assertTrue(VfsUtil.exist(pathResolver.getBasePath() + fileItem.getPath()));
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					// ignore me;
				}
			}
		}
	}

	@AfterClass
	public static void afterClassFileServiceTest() {
		VfsUtil.delete(BASE_FOLDER);
	}
}
