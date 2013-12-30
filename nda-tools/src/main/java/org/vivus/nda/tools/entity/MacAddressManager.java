package org.vivus.nda.tools.entity;

public class MacAddressManager extends AManager {

	public int insert(MacAddress macAddress) {
		getSession().insert(macAddress);
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
