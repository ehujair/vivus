package org.vivus.adb.impl;

import org.apache.ibatis.session.SqlSession;

public abstract class AbstractManager {
	SqlSession sqlSession;

	public AbstractManager(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

}
