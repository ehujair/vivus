package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.entity.FileManager;
import org.vivus.nda.tools.file.VfsUtil;

public class FileDeleteCmd implements ICommand<Void> {
	String fileId;

	public FileDeleteCmd(String fileId) {
		this.fileId = fileId;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		FileManager fileManager = commandContext.getFileManager();
		FileItem fileItem = fileManager.load(fileId);
		fileManager.delete(fileId);
		VfsUtil.delete(commandContext.getPathResolver().getBasePath() + fileItem.getPath());
		return null;
	}

}
