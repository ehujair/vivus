package org.vivus.jetty;

import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringEscapeUtils;

public class SystemPropertiesTest {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		for (Entry<Object, Object> property : properties.entrySet()) {
			System.out.println(property.getKey() + ": " + property.getValue());
		}
		System.out.println("---------- 常用/重要 ----------");
		System.out.println("file.separator: " + System.getProperty("file.separator"));
		System.out.println("line.separator: " + StringEscapeUtils.escapeJava(System.getProperty("line.separator")));
		System.out.println("path.separator: " + System.getProperty("path.separator"));
		System.out.println("java.class.path: " + System.getProperty("java.class.path"));
		System.out.println("java.home: " + System.getProperty("java.home"));
		System.out.println("java.vendor: " + System.getProperty("java.vendor"));
		System.out.println("java.vendor.url: " + System.getProperty("java.vendor.url"));
		System.out.println("java.version: " + System.getProperty("java.version"));
		System.out.println("os.arch: " + System.getProperty("os.arch"));
		System.out.println("os.name: " + System.getProperty("os.name"));
		System.out.println("os.version: " + System.getProperty("os.version"));
		System.out.println("user.dir: " + System.getProperty("user.dir"));
		System.out.println("user.home: " + System.getProperty("user.home"));
		System.out.println("user.name: " + System.getProperty("user.name"));
	}
}
