package org.vivus.nda.tools.query;

import java.util.List;

public interface IQuery<T> {
	public enum Order {
		ASC, DESC;
	}

	List<T> list();

	List<T> list(Page page);

	T unique();

	long count();
}
