package org.vivus.exception;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.junit.Test;

public class ResourceBundleTest {
	@Test
	public void test() {
		ResourceBundle rb = ResourceBundle.getBundle(BusinessExceptionDescriptorFactory.BASE_NAME);
		System.out.println(rb.getLocale().toString());
		Enumeration<String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			System.out.println(key);
			System.out.println(rb.getString(key));
		}
	}
}
