package org.vivus.nda.context;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CommandContext {
	SqlSession session;
	SqlSessionFactory sessionFactory;

	public SqlSession getSession() {
		if (session == null) {
			session = getSessionFactory().openSession();
		}
		return session;
	}

	public SqlSessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new RuntimeException("no SessionFactory configured");
		}
		return sessionFactory;
	}

	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
