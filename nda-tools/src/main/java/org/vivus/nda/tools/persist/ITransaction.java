package org.vivus.nda.tools.persist;

public interface ITransaction {
	void commit();

	void rollback();
}
