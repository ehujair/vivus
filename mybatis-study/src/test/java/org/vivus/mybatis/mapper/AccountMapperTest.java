package org.vivus.mybatis.mapper;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.vivus.mybatis.domain.Account;

public class AccountMapperTest {
	static SqlSessionFactory sqlSessionFactory;
	static final String CONFIG_FILE = "mybatis-config.xml";
	SqlSession session;

	@BeforeClass
	public static void beforeClass() throws IOException {
		Reader reader = Resources.getResourceAsReader(CONFIG_FILE);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		Assert.assertNotNull(sqlSessionFactory);
		SqlSession s = sqlSessionFactory.openSession();
		int i = s.update("createTable");
		System.out.println(i);
	}

	@AfterClass
	public static void afterClass() {
		sqlSessionFactory = null;
	}

	@Before
	public void before() {
		session = sqlSessionFactory.openSession();
	}

	@After
	public void after() {
		if (session != null) {
			session.close();
		}
	}
	
	@Test
	public void testTemp() {
		System.out.println("Temp");
	}

//	@Test
	public void test() {
		String userName = "acc-1";
		String pwd = "pwd-1";
		String upPwd = "pwd-2";
		Account account = new Account();
		account.setUserName(userName);
		account.setPassword(pwd);

		Account a = session.selectOne("selectAccount", account.getUserName());
		Assert.assertTrue(a == null);

		session.insert("insertAccount", account);
		Account account1 = session.selectOne("selectAccount", account.getUserName());
		Assert.assertTrue(userName.equals(account1.getUserName()));
		Assert.assertTrue(pwd.equals(account1.getPassword()));

		account.setPassword(upPwd);
		session.update("updateAccount", account);
		Account account2 = session.selectOne("selectAccount", account.getUserName());
		Assert.assertTrue(userName.equals(account2.getUserName()));
		Assert.assertTrue(upPwd.equals(account2.getPassword()));

		session.delete("deleteAccount", account);
		Account account3 = session.selectOne("selectAccount", account.getUserName());
		Assert.assertTrue(account3 == null);
	}
}
