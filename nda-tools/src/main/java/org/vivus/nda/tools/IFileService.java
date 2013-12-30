package org.vivus.nda.tools;

import java.io.InputStream;
import java.io.OutputStream;

import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.file.IFileQuery;

public interface IFileService {
	FileItem upload(String fileName, InputStream inputStream);

	FileItem download(String fileId, OutputStream outputStream);

	void delete(String fileId);

	FileItem load(String fileId);

	IFileQuery createFileQuery();
}
