package org.vivus.nda.tools.entity;

import java.util.List;

import org.vivus.nda.tools.file.IFileQuery;
import org.vivus.nda.tools.query.Page;

public class FileManager extends AManager {
	public FileItem save(FileItem fileItem) {
		getSession().insert(fileItem);
		return fileItem;
	}

	public FileItem load(String id) {
		return getSession().load(id, FileItem.class);
	}

	public void update(FileItem fileItem) {
		getSession().update(fileItem);
	}

	public void delete(String id) {
		getSession().delete(id, FileItem.class);
	}

	public List<FileItem> findByCriteria(IFileQuery fileQuery, Page page) {
		return getSession().list(fileQuery, page, FileItem.class);
	}
	
	public long countByCriteria(IFileQuery fileQuery) {
		return getSession().count(fileQuery, FileItem.class);
	}
}
