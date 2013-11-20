package org.vivus.nda.tools.persist;

import java.util.List;

import org.vivus.nda.tools.query.Page;

public interface ISession {
	void close();

	<T> T load(String id, Class<T> clazz);

	<T> void insert(T obj);

	<T> void update(T obj);

	<T> void delete(String id, Class<T> clazz);

	<T> List<T> list(Object criteria, Page page, Class<T> clazz);

	<T> long count(Object criteria, Class<T> clazz);
}
