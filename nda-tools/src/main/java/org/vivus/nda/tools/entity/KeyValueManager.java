package org.vivus.nda.tools.entity;

public class KeyValueManager extends AManager {
	public KeyValue load(String key) {
		return getSession().load(key);
	}

	public String insert(KeyValue keyValue) {
		getSession().insert(keyValue);
		return keyValue.getKey();
	}

	public void update(KeyValue keyValue) {
		getSession().update(keyValue);
	}

	public void delete(String key) {
		getSession().delete(key);
	}
}
