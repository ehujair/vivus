package org.vivus.mybatis.session;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.jndi.JndiDataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Assert;
import org.junit.Test;

public class SqlSessionFactoryTest {
	@Test
	public void buildSqlSessionFactory1() throws IOException {
		String resource = "mybatis-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		Assert.assertNotNull(sqlSessionFactory);
	}

	@Test
	public void buildSqlSessionFactory2() {
//		DataSourceFactory dataSourceFactory = new DataSourceFactory() {
//			DataSource dataSource;
//
//			public void setProperties(Properties props) {
//			}
//
//			public DataSource getDataSource() {
//				return dataSource;
//			}
//
//		};
//		DataSource dataSource = dataSourceFactory.getDataSource();
//		DataSource dataSource = new JndiDataSourceFactory().getDataSource();
		DataSource dataSource = new PooledDataSourceFactory().getDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		// configuration.addMapper(BlogMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		Assert.assertNotNull(sqlSessionFactory);
	}

	@Test
	public void buildSqlSessionFactory3() {
		Configuration config = new Configuration();
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
		Assert.assertNotNull(sqlSessionFactory);
	}

}
