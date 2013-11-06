package org.vivus.nda.tools.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.vivus.nda.tools.IEngine;
import org.vivus.nda.tools.context.ACommandInterceptor;
import org.vivus.nda.tools.context.CommandContextInterceptor;
import org.vivus.nda.tools.context.LogInterceptor;
import org.vivus.nda.tools.impl.AService;
import org.vivus.nda.tools.impl.Engine;
import org.vivus.nda.tools.impl.DbMacGenerator;
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
		initCommandExecutors();
		initServices();
		initMacGenerator();
		return new Engine(this);
	}

	protected void initSessionFactory() {
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ResourceUtil
				.getResourceAsStream(mybatisConfigFile));
		sessionFactory = new MybatisSessionFactory(sqlSessionFactory);
	}

	protected void initTransactionFactory() {
		transactionFactory = new MybatisTransactionFactory();
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
	}

	protected void initService(Object service) {
		if (service instanceof AService) {
			((AService) service).setCommandExecutor(commandExecutor);
		}
	}

	protected void initMacGenerator() {
		macGenerator = new DbMacGenerator(commandExecutor);
	}

	// getter & setter
	public String getMybatisConfigFile() {
		return mybatisConfigFile;
	}

	public void setMybatisConfigFile(String mybatisConfigFile) {
		this.mybatisConfigFile = mybatisConfigFile;
	}

}
