package org.vivus.nda.tools.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.vivus.nda.tools.IEngine;
import org.vivus.nda.tools.context.ACommandInterceptor;
import org.vivus.nda.tools.context.CommandContextInterceptor;
import org.vivus.nda.tools.context.LogInterceptor;
import org.vivus.nda.tools.file.DailyLocalPathResolver;
import org.vivus.nda.tools.impl.AService;
import org.vivus.nda.tools.impl.DbIdGenerator;
import org.vivus.nda.tools.impl.DbMacGenerator;
import org.vivus.nda.tools.impl.Engine;
import org.vivus.nda.tools.persist.mybatis.MybatisSessionFactory;
import org.vivus.nda.tools.persist.mybatis.MybatisTransactionFactory;
import org.vivus.nda.tools.util.ResourceUtil;

public class Configuration extends AConfiguration {
	protected List<ACommandInterceptor> defaultCommandInterceptors;
	protected String mybatisConfigFile = "mybatis.xml";

	@Override
	protected Collection<? extends ACommandInterceptor> getDefaultCommandInterceptors() {
		if (defaultCommandInterceptors == null) {
			defaultCommandInterceptors = new ArrayList<ACommandInterceptor>();
			defaultCommandInterceptors.add(new LogInterceptor());
			defaultCommandInterceptors.add(new CommandContextInterceptor(this));
		}
		return defaultCommandInterceptors;
	}

	@Override
	public IEngine buildEngine() {
		initSessionFactory();
		initTransactionFactory();
		initPathResolver();
		initCommandExecutors();
		initServices();
		initMacGenerator();
		initIdGenerator();
		return new Engine(this);
	}

	protected void initSessionFactory() {
		setDatabaseType(MybatisSessionFactory.H2);
		Properties properties = new Properties();
		if (databaseType != null) {
			properties.put("limitBefore",
					MybatisSessionFactory.databaseSpecificLimitBeforeStatements.get(databaseType));
			properties.put("limitAfter",
					MybatisSessionFactory.databaseSpecificLimitAfterStatements.get(databaseType));
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(
				ResourceUtil.getResourceAsStream(mybatisConfigFile), properties);
		sessionFactory = new MybatisSessionFactory(sqlSessionFactory);
	}

	protected void initTransactionFactory() {
		transactionFactory = new MybatisTransactionFactory();
	}

	protected void initPathResolver() {
		pathResolver = new DailyLocalPathResolver();
	}

	protected void initCommandExecutors() {
		initCommandInterceptors();
		initCommandExecutor();
	}

	protected void initCommandInterceptors() {
		if (commandInterceptors == null) {
			if (customPreCommandInterceptors != null) {
				commandInterceptors = new ArrayList<ACommandInterceptor>(
						customPreCommandInterceptors);
			} else {
				commandInterceptors = new ArrayList<ACommandInterceptor>();
			}
			commandInterceptors.addAll(getDefaultCommandInterceptors());
			if (customPostCommandInterceptors != null) {
				commandInterceptors.addAll(customPostCommandInterceptors);
			}
			commandInterceptors.add(actualCommandExecutor);
		}
	}

	protected void initCommandExecutor() {
		if (commandExecutor == null) {
			commandExecutor = initCommandInterceptorChain(commandInterceptors);
		}
	}

	protected ACommandInterceptor initCommandInterceptorChain(List<ACommandInterceptor> chain) {
		if (chain == null || chain.isEmpty()) {
			throw new RuntimeException("invalid command interceptor chain configuration: " + chain);
		}
		for (int i = 0; i < chain.size() - 1; i++) {
			chain.get(i).setNext(chain.get(i + 1));
		}
		return chain.get(0);
	}

	protected void initServices() {
		initService(macAddressService);
		initService(fileService);
	}

	protected void initService(Object service) {
		if (service instanceof AService) {
			((AService) service).setCommandExecutor(commandExecutor);
		}
	}

	protected void initMacGenerator() {
		macGenerator = new DbMacGenerator(commandExecutor);
	}
	
	protected void initIdGenerator() {
		idGenerator = new DbIdGenerator(commandExecutor);
	}

	// getter & setter
	public String getMybatisConfigFile() {
		return mybatisConfigFile;
	}

	public void setMybatisConfigFile(String mybatisConfigFile) {
		this.mybatisConfigFile = mybatisConfigFile;
	}

}
