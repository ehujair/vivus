package org.vivus.nda.tools.persist.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.vivus.nda.tools.persist.ISession;
import org.vivus.nda.tools.persist.ISessionFactory;

public class MybatisSessionFactory implements ISessionFactory {
	SqlSessionFactory sqlSessionFactory;

	public MybatisSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public ISession openSession() {
		return new MybatisSession(sqlSessionFactory.openSession());
	}
}
