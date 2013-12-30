package org.vivus.adb;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.vivus.adb.impl.MacAddressManager;

public class PersistenceTest {
	AdbEngine adbEngine = new Configuration().build();

	@BeforeClass
	public static void beforeClassPersistenceTest() {
		// create table
		
	}
	
	@Test
	public void testInsert() {
		SqlSession session = adbEngine.getSqlSessionFactory().openSession();
		MacAddressManager macAddressManager = new MacAddressManager(session);
		try {
			MacAddress macAddress = new MacAddress();
			macAddress.setMac(adbEngine.getMacGenerator().generateMac());
			macAddress.setWriteTime(new Date());
			macAddressManager.insert(macAddress);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw new AdbException("can not insert MacAddress", e);
		} finally {
			session.close();
		}
	}

	@AfterClass
	public static void afterClass() {
		// drop table
	}
}
