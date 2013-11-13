package org.vivus.adb.impl;

import org.apache.ibatis.session.SqlSession;
import org.vivus.adb.MacAddress;

public class MacAddressManager extends AbstractManager {
	public MacAddressManager(SqlSession sqlSession) {
		super(sqlSession);
	}

	public String insert(MacAddress macAddress) {
		getSqlSession().insert("insertMacAddress", macAddress);
		return macAddress.getMac();
	}

	public void update(MacAddress macAddress) {

	}

	public void delete(String mac) {

	}

	public MacAddress load(String mac) {
		return null;
	}
}
