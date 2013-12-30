package org.vivus.nda.tools.cmd;

import java.io.InputStream;

import org.apache.commons.vfs2.FileObject;
import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.context.IIdGenerator;
import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.file.IPathResolver;
import org.vivus.nda.tools.file.VfsUtil;
import org.vivus.nda.tools.util.PathUtil;

public class FileUploadCmd implements ICommand<FileItem> {
	String fileName;
	InputStream inputStream;

	public FileUploadCmd(String fileName, InputStream inputStream) {
		this.fileName = fileName;
		this.inputStream = inputStream;
	}

	@Override
	public FileItem execute(CommandContext commandContext) {
		// TODO,handle exception,rollback file
		// save file
		// generate id
		IIdGenerator idGenerator = commandContext.getConfiguration().getIdGenerator();
		String id = idGenerator.generateId();
		// get save path
		IPathResolver pathResolver = commandContext.getPathResolver();
		String path = pathResolver.getPath() + id + PathUtil.EXT_SEPARATOR
				+ PathUtil.getExtension(fileName);
		// write file content
		FileObject target = VfsUtil.resolveFile(pathResolver.getBasePath() + path);
		VfsUtil.writeContent(inputStream, target);
		// save file info
		FileItem fileItem = new FileItem();
		fileItem.setId(id);
		fileItem.setName(fileName);
		fileItem.setType(PathUtil.getExtension(fileName));
		fileItem.setPath(path);
		fileItem.setSize(VfsUtil.getSize(target));
		commandContext.getFileManager().save(fileItem);
		return fileItem;
	}

}
