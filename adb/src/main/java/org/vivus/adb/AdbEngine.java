package org.vivus.adb;

import org.apache.ibatis.session.SqlSessionFactory;

public class AdbEngine {
	Configuration configuration;
	SqlSessionFactory sqlSessionFactory;
	MacGenerator macGenerator;

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setMacGenerator(MacGenerator macGenerator) {
		this.macGenerator = macGenerator;
	}

	public MacGenerator getMacGenerator() {
		return macGenerator;
	}

}
