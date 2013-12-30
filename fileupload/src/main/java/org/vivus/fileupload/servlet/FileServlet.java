package org.vivus.fileupload.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.vivus.fileupload.FileUploadServer;
import org.vivus.nda.tools.IFileService;

public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 7085108558887891561L;
	
	IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		fileService = FileUploadServer.getEngine().getFileService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = getServletConfig().getServletContext();
		File repository = new File(ClassLoader.getSystemResource("").toString() + "tempDir/");
		if (!repository.exists()) {
			repository.mkdirs();
		}
		DiskFileItemFactory factory = newDiskFileItemFactory(servletContext, repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(req);
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();
			    if (item.isFormField()) {
			        processFormField(item);
			    } else {
			        processUploadedFile(item);
			    }
			}
		} catch (FileUploadException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void processFormField(FileItem item) {
		if (item.isFormField()) {
		    String name = item.getFieldName();
		    String value = item.getString();
		    System.out.println(name + ": " + value);
		}
	}
	
	private void processUploadedFile(FileItem item) throws Exception {
		if (!item.isFormField()) {
			String fieldName = item.getFieldName();
			String fileName = item.getName();
			String contentType = item.getContentType();
			boolean isInMemory = item.isInMemory();
			long sizeInBytes = item.getSize();
			boolean writeToFile = false;
			if (writeToFile) {
				File uploadedFile = new File("");
				item.write(uploadedFile);
			} else {
				System.out.println("uploading file " + fileName + " ...");
				System.out.println(new String(item.get(), "GBK"));
				InputStream inputStream = item.getInputStream();
				try {
					fileService.upload(fileName, inputStream);
				} finally {
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (Exception e) {
							e.printStackTrace();
							// ignore me
						}
					}
				}
			}
		}
	}

	public static DiskFileItemFactory newDiskFileItemFactory(ServletContext servletContext, File repository) {
		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup
				.getFileCleaningTracker(servletContext);
		DiskFileItemFactory factory = new DiskFileItemFactory(
				DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository);
		factory.setFileCleaningTracker(fileCleaningTracker);
		return factory;
	}
}
