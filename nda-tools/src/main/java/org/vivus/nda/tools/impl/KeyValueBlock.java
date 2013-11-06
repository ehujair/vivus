package org.vivus.nda.tools.impl;

public class KeyValueBlock {
	int nextValue;
	int lastValue;

	public KeyValueBlock(int nextValue, int lastValue) {
		this.nextValue = nextValue;
		this.lastValue = lastValue;
	}

	public int getNextValue() {
		return nextValue;
	}

	public void setNextValue(int nextValue) {
		this.nextValue = nextValue;
	}

	public int getLastValue() {
		return lastValue;
	}

	public void setLastValue(int lastValue) {
		this.lastValue = lastValue;
	}

}
