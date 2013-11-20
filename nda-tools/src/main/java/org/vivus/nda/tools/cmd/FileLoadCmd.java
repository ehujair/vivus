package org.vivus.nda.tools.cmd;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.entity.FileItem;

public class FileLoadCmd implements ICommand<FileItem> {
	String fileId;

	public FileLoadCmd(String fileId) {
		this.fileId = fileId;
	}

	@Override
	public FileItem execute(CommandContext commandContext) {
		return commandContext.getFileManager().load(fileId);
	}

}
