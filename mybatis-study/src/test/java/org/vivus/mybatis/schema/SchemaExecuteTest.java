package org.vivus.mybatis.schema;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SchemaExecuteTest {
	static final String createResource = "db/create/mybatis-study-h2-create.sql";
	static final String dropResource = "db/drop/mybatis-study-h2-drop.sql";
	DataSource dataSource;

	@Before
	public void schemaExecuteBefore() {
		dataSource = new PooledDataSource("org.h2.Driver", "jdbc:h2:mem:anyi", "sa", "");
	}

	@After
	public void schemaExecuteAfter() {
		dataSource = null;
	}

	@Test
	public void testCreate() throws IOException, SQLException {
		String createSql = readFile(createResource);
		String dropSql = readFile(dropResource);
		Connection connection = null;
		Statement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute(createSql);
			statement.execute(dropSql);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Test
	public void testCreate2() throws IOException, SQLException {
		String createSql = readFile(createResource);
		 String dropSql = readFile(dropResource);
		Connection connection = null;
		Statement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(createSql);
			statement.executeUpdate(dropSql);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	public static String readFile(String fileName) throws IOException {
		InputStream is = null;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		;
		if (cl != null) {
			is = cl.getResourceAsStream(fileName);
		}
		if (is == null) {
			cl = SchemaExecuteTest.class.getClassLoader();
			is = cl.getResourceAsStream(fileName);
		}
		if (is == null)
			throw new RuntimeException("can't read resource: " + fileName);
		try {
			return readFile(is);
		} finally {
			is.close();
		}
	}

	public static String readFile(InputStream inputStream) throws IOException {
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[16 * 1024];
		int bytesRead = inputStream.read(b);
		while (bytesRead != -1) {
			sb.append(new String(b));
			bytesRead = inputStream.read(b);
		}
		return sb.toString();
	}
}
