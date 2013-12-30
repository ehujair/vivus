package org.vivus.nda.tools.cmd;

import java.io.OutputStream;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.file.VfsUtil;

public class FileDownloadCmd implements ICommand<FileItem> {
	String fileId;
	OutputStream outputStream;

	public FileDownloadCmd(String fileId, OutputStream outputStream) {
		this.fileId = fileId;
		this.outputStream = outputStream;
	}

	@Override
	public FileItem execute(CommandContext commandContext) {
		FileItem fileItem = commandContext.getFileManager().load(fileId);
		String path = commandContext.getPathResolver().getBasePath() + fileItem.getPath();
		VfsUtil.writeContent(path, outputStream);
		return fileItem;
	}

}
