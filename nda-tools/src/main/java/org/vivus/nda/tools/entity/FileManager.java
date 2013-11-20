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
		return getSession().load(id);
	}

	public void update(FileItem fileItem) {
		getSession().update(fileItem);
	}

	public void delete(String id) {
		getSession().delete(id);
	}

	public List<FileItem> findByCriteria(IFileQuery fileQuery, Page page) {
		return getSession().list(fileQuery, page);
	}
	
	public long countByCriteria(IFileQuery fileQuery) {
		return getSession().count(fileQuery);
	}
}
