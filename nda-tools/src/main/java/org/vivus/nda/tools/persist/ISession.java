package org.vivus.nda.tools.persist;

public interface ISession {
	void close();

	<T> T load(String id, Class<T> clazz);

	<T> void insert(T obj);

	<T> void update(T obj);

	<T> void delete(String id, Class<T> clazz);
}
