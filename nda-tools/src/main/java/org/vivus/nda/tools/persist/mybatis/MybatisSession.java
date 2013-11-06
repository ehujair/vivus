package org.vivus.nda.tools.persist.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.vivus.nda.tools.persist.ISession;
import org.vivus.nda.tools.persist.ITransaction;

public class MybatisSession implements ISession, ITransaction {
	SqlSession sqlSession;

	public MybatisSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void close() {
		sqlSession.close();
	}

	@Override
	public <T> T load(String id, Class<T> clazz) {
		return sqlSession.selectOne(getStatement("load", clazz), id);
	}

	private String getStatement(String prefix, Class<?> clazz) {
		return prefix + clazz.getSimpleName(); 
	}

	@Override
	public <T> void insert(T obj) {
		sqlSession.insert(getStatement("insert", obj.getClass()), obj);
	}

	@Override
	public <T> void update(T obj) {
		sqlSession.update(getStatement("update", obj.getClass()), obj);
	}

	@Override
	public <T> void delete(String id, Class<T> clazz) {
		sqlSession.delete(getStatement("delete", clazz), id);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public void rollback() {
		sqlSession.rollback();
	}

}
