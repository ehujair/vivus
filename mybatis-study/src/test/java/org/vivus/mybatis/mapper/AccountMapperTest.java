package org.vivus.mybatis.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
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
	static int id = 0;
	static SqlSessionFactory sqlSessionFactory;
	static final String CONFIG_FILE = "mybatis-config.xml";
	SqlSession session;

	@BeforeClass
	public static void beforeClass() throws IOException {
		Reader reader = Resources.getResourceAsReader(CONFIG_FILE);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		Assert.assertNotNull(sqlSessionFactory);
		SqlSession s = sqlSessionFactory.openSession();
		s.update("createTable");
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

	@Test
	public void test() {
		String userName = "acc-1";
		String pwd = "pwd-1";
		String upPwd = "pwd-2";
		Account account = new Account();
		account.setId(generateId());
		account.setUserName(userName);
		account.setPassword(pwd);

		Account a = session.selectOne("selectAccount", account.getId());
		Assert.assertTrue(a == null);

		session.insert("insertAccount", account);
		Account account1 = session.selectOne("selectAccount", account.getId());
		Assert.assertTrue(userName.equals(account1.getUserName()));
		Assert.assertTrue(pwd.equals(account1.getPassword()));

		account.setPassword(upPwd);
		session.update("updateAccount", account);
		Account account2 = session.selectOne("selectAccount", account.getId());
		Assert.assertTrue(userName.equals(account2.getUserName()));
		Assert.assertTrue(upPwd.equals(account2.getPassword()));

		List<Object> selectList = session.selectList("selectAccountDynamic", account);
		System.out.println(selectList.size());
		session.selectList("selectAccountDynamic", new Account());

		session.delete("deleteAccount", account);
		Account account3 = session.selectOne("selectAccount", account.getId());
		Assert.assertTrue(account3 == null);
	}

	@Test
	public void testMapper() {
		String userName = "acc-1";
		String pwd = "pwd-1";
		String upPwd = "pwd-2";
		Account account = new Account();
		account.setId(generateId());
		account.setUserName(userName);
		account.setPassword(pwd);

		AccountMapper accountMapper = session.getMapper(AccountMapper.class);

		Account a = accountMapper.selectAccount(account.getId());
		Assert.assertTrue(a == null);

		System.out.println("insertAccount: " + accountMapper.insertAccount(account));
		Account account1 = accountMapper.selectAccount(account.getId());
		Assert.assertTrue(userName.equals(account1.getUserName()));
		Assert.assertTrue(pwd.equals(account1.getPassword()));

		account.setPassword(upPwd);
		System.out.println("updateAccount: " + accountMapper.updateAccount(account));
		Account account2 = accountMapper.selectAccount(account.getId());
		Assert.assertTrue(userName.equals(account2.getUserName()));
		Assert.assertTrue(upPwd.equals(account2.getPassword()));

		List<Account> selectList = accountMapper.selectAccountDynamic(account);
		System.out.println("selectAccountDynamic size: " + selectList.size());
		accountMapper.selectAccountDynamic(new Account());

		System.out.println("deleteAccount: " + accountMapper.deleteAccount(account));
		Account account3 = accountMapper.selectAccount(account.getId());
		Assert.assertTrue(account3 == null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testInsert() {
		String userName = "acc-1";
		String pwd = "pwd-1";
		Account account = new Account();
		account.setId(generateId());
		account.setUserName(userName);
		account.setPassword(pwd);

		AccountMapper accountMapper = session.getMapper(AccountMapper.class);
		accountMapper.insert(account);
		Account a = accountMapper.selectAccount(account.getId());
		Assert.assertTrue(a != null);
		Assert.assertTrue(userName.equals(a.getUserName()));
		Assert.assertTrue(pwd.equals(a.getPassword()));
	}

	@Test(expected = TooManyResultsException.class)
	public void testMultipleData() {
		String userName = "acc-1";
		String pwd = "pwd-1";
		Account account = new Account();
		account.setId(generateId());
		account.setUserName(userName);
		account.setPassword(pwd);
		AccountMapper accountMapper = session.getMapper(AccountMapper.class);

		accountMapper.insertAccount(account);
		Account a = accountMapper.selectAccountByName(account.getUserName());
		Assert.assertTrue(a != null);
		Assert.assertTrue(userName.equals(a.getUserName()));

		account.setId(generateId());
		accountMapper.insertAccount(account);
		a = accountMapper.selectAccountByName(account.getUserName());
		Assert.assertTrue(a != null);
		Assert.assertTrue(userName.equals(a.getUserName()));
	}

	@Test
	public void testGenerateId() {
		System.out.println(generateId());
		System.out.println(id);
	}

	@Test
	public void testClassName() {
		System.out.println(AccountMapperTest.class.getName());
		System.out.println(AccountMapperTest.class.getCanonicalName());
		System.out.println(AccountMapperTest.class.getSimpleName());
	}

	public static String generateId() {
		return AccountMapperTest.class.getSimpleName() + (id++);
	}
}
