package org.vivus.nda.tools.entity;

import java.util.Date;

public class MacAddress {
	int mac;
	Date writeTime;

	public int getMac() {
		return mac;
	}

	public void setMac(int mac) {
		this.mac = mac;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

}
