package org.vivus.adb;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.vivus.adb.util.ResourceUtil;

public class Configuration {
	// database
	String mybatisConfigResource = "mybatis.xml";;
	String macGeneratorClass = "org.vivus.adb.DefaultMacGenerator";

	public Configuration setMybatisConfigResource(String resource) {
		mybatisConfigResource = resource;
		return this;
	}

	public Configuration setMacGeneratorClass(String macGeneratorClass) {
		this.macGeneratorClass = macGeneratorClass;
		return this;
	}

	public AdbEngine build() {
		AdbEngine adbEngine = new AdbEngine();
		adbEngine.setConfiguration(this);
		adbEngine.setSqlSessionFactory(buildSqlSessionFactory());
		adbEngine.setMacGenerator(buildMacGenerator());
		return adbEngine;
	}

	private SqlSessionFactory buildSqlSessionFactory() {
		if (isBlank(mybatisConfigResource)) {
			throw new AdbException("please specify the mybatisConfigResource");
		}
		return new SqlSessionFactoryBuilder().build(ResourceUtil
				.getResourceAsStream(mybatisConfigResource));
	}

	private MacGenerator buildMacGenerator() {
		if (isBlank(macGeneratorClass)) {
			throw new AdbException("please specify the macGeneratorClass");
		}
		try {
			Class<?> clazz = Class.forName(macGeneratorClass);
			return (MacGenerator) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new AdbException("class " + macGeneratorClass + " not found", e);
		} catch (InstantiationException e) {
			throw new AdbException("can not instantiate class " + macGeneratorClass, e);
		} catch (IllegalAccessException e) {
			throw new AdbException("illegal access class " + macGeneratorClass, e);
		}
	}

	private static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}
}
