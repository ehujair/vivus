package org.vivus.nda.tools.file;

import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.query.IQuery;

public interface IFileQuery extends IQuery<FileItem> {
	IFileQuery id(String id);

	IFileQuery name(String name);

	IFileQuery nameLike(String nameLike);

	IFileQuery type(String type);

	IFileQuery sizeLessThan(long size);

	IFileQuery sizeGreaterThan(long size);

	IFileQuery sizeBetween(long min, long max);

	IFileQuery orderById();

	IFileQuery orderById(Order order);

	IFileQuery orderByName();

	IFileQuery orderByName(Order order);

	IFileQuery orderByPath();

	IFileQuery orderByPath(Order order);

	IFileQuery orderByType();

	IFileQuery orderByType(Order order);

	IFileQuery orderBySize();

	IFileQuery orderBySize(Order order);
}
