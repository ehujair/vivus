package org.vivus.nda.tools.persist.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.vivus.nda.tools.persist.ISession;
import org.vivus.nda.tools.persist.ITransaction;
import org.vivus.nda.tools.query.Page;

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
	public <T> T load(String id) {
		return sqlSession.selectOne(getStatement("load"), id);
	}

	@Override
	public void insert(Object obj) {
		sqlSession.insert(getStatement("insert"), obj);
	}

	@Override
	public void update(Object obj) {
		sqlSession.update(getStatement("update"), obj);
	}

	@Override
	public void delete(String id) {
		sqlSession.delete(getStatement("delete"), id);
	}

	@Override
	public <T> List<T> list(Object criteria, Page page) {
		return sqlSession.selectList(getStatement("findByCriteria"), criteria,
				new RowBounds(page.getFirstResult(), page.getMaxResults()));
	}

	@Override
	public long count(Object criteria) {
		return sqlSession.selectOne(getStatement("countByCriteria"), criteria);
	}

	@Override
	public void commit() {
		sqlSession.commit();
	}

	@Override
	public void rollback() {
		sqlSession.rollback();
	}

	private String getStatement(String prefix) {
		return getCallerStackTrace().getClassName() + "." + prefix;
	}

	private StackTraceElement getCallerStackTrace() {
		StackTraceElement stack[] = new Throwable().getStackTrace();
		int i = 0;
		String className = getClass().getName();
		while (i < stack.length) {
			StackTraceElement frame = stack[i];
			String cname = frame.getClassName();
			if (cname.equals(className)) {
				break;
			}
			i++;
		}
		while (i < stack.length) {
			StackTraceElement frame = stack[i];
			String cname = frame.getClassName();
			if (!cname.equals(className)) {
				return frame;
			}
			i++;
		}
		return null;
	}
}
