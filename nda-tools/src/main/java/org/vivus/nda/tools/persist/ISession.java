package org.vivus.nda.tools.persist;

import java.util.List;

import org.vivus.nda.tools.query.Page;

public interface ISession {
	void close();

	<T> T load(String id);

	void insert(Object obj);

	void update(Object obj);

	void delete(String id);

	<T> List<T> list(Object criteria, Page page);

	long count(Object criteria);
}
