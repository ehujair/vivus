package org.vivus.nda.tools.persist.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.vivus.nda.tools.persist.ISession;
import org.vivus.nda.tools.persist.ISessionFactory;

public class MybatisSessionFactory implements ISessionFactory {
	public static final String H2 = "h2";
	public static final String MYSQL = "mysql";
	public static final String POSTGRES = "postgres";
	public static final String ORACLE = "oracle";
	public static final String DB2 = "db2";
	public static final String MSSQL = "mssql";
	
	SqlSessionFactory sqlSessionFactory;
	public static final Map<String, String> databaseSpecificLimitBeforeStatements = new HashMap<String, String>();
	public static final Map<String, String> databaseSpecificLimitAfterStatements = new HashMap<String, String>();

	static {
		// h2
		databaseSpecificLimitBeforeStatements.put(H2, "");
		databaseSpecificLimitAfterStatements.put(H2, "LIMIT #{maxResults} OFFSET #{firstResult}");

		// mysql specific
		databaseSpecificLimitBeforeStatements.put(MYSQL, "");
		databaseSpecificLimitAfterStatements.put(MYSQL, "LIMIT #{maxResults} OFFSET #{firstResult}");

		// postgres specific
		databaseSpecificLimitBeforeStatements.put(POSTGRES, "");
		databaseSpecificLimitAfterStatements.put(POSTGRES, "LIMIT #{maxResults} OFFSET #{firstResult}");

		// oracle
		databaseSpecificLimitBeforeStatements.put(ORACLE, "select * from ( select _a.*, ROWNUM rnum from (");
		databaseSpecificLimitAfterStatements.put(ORACLE, "  ) _a where ROWNUM < #{lastRow}) where rnum  >= #{firstRow}");

		// db2
		databaseSpecificLimitBeforeStatements.put(DB2, ""); // TODO!
		databaseSpecificLimitAfterStatements.put(DB2, "");

		// mssql
		databaseSpecificLimitBeforeStatements.put(MSSQL, ""); // TODO!
		databaseSpecificLimitAfterStatements.put(MSSQL, "");
	}
	
	public MybatisSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public ISession openSession() {
		return new MybatisSession(sqlSessionFactory.openSession());
	}
}
