package org.vivus.nda.tools.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.vivus.nda.tools.IFileService;
import org.vivus.nda.tools.cmd.FileDeleteCmd;
import org.vivus.nda.tools.cmd.FileDownloadCmd;
import org.vivus.nda.tools.cmd.FileLoadCmd;
import org.vivus.nda.tools.cmd.FileUploadCmd;
import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.file.IFileQuery;
import org.vivus.nda.tools.impl.query.FileQuery;

public class FileService extends AService implements IFileService {

	@Override
	public FileItem upload(String fileName, InputStream inputStream) {
		return getCommandExecutor().execute(new FileUploadCmd(fileName, inputStream));
	}

	@Override
	public FileItem download(String fileId, OutputStream outputStream) {
		return getCommandExecutor().execute(new FileDownloadCmd(fileId, outputStream));
	}

	@Override
	public void delete(String fileId) {
		getCommandExecutor().execute(new FileDeleteCmd(fileId));
	}

	@Override
	public FileItem load(String fileId) {
		return getCommandExecutor().execute(new FileLoadCmd(fileId));
	}

	@Override
	public IFileQuery createFileQuery() {
		return new FileQuery(getCommandExecutor());
	}

}
