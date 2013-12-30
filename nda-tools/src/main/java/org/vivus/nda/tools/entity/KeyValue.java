package org.vivus.nda.tools.entity;

public class KeyValue {
	public static final String KEY_MAC_ADDRESS = "MAC_ADDRESS";
	public static final String KEY_ID = "ID";

	String key;
	int minValue = 1;
	int maxValue = Integer.MAX_VALUE;
	int currentValue = 1;
	boolean recycle = false;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public boolean isRecycle() {
		return recycle;
	}

	public void setRecycle(boolean recycle) {
		this.recycle = recycle;
	}

}
