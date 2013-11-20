package org.vivus.nda.tools.impl.query;

import org.vivus.nda.tools.query.IQueryProperty;

public enum FileItemProperty implements IQueryProperty {
	ID("fileItem.ID"),
	NAME("fileItem.NAME"),
	PATH("fileItem.PATH"),
	TYPE("fileItem.TYPE"),
	SIZE("fileItem.SIZE");

	String name;

	FileItemProperty(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
