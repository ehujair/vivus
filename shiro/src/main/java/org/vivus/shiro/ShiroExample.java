package org.vivus.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;

public class ShiroExample {
	public static void main(String[] args) {
		// 1.装入INI配置
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2. 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		// 3. 使其可访问
		SecurityUtils.setSecurityManager(securityManager);
	}
}
