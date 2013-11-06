package org.vivus.nda.tools.config;

import java.util.Collection;
import java.util.List;

import org.vivus.nda.tools.IEngine;
import org.vivus.nda.tools.IMacAddressService;
import org.vivus.nda.tools.context.ACommandInterceptor;
import org.vivus.nda.tools.context.CommandExecutor;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.context.IMacGenerator;
import org.vivus.nda.tools.context.ITransactionFactory;
import org.vivus.nda.tools.impl.MacAddressService;
import org.vivus.nda.tools.persist.ISessionFactory;

public abstract class AConfiguration {
	// Service
	protected IMacAddressService macAddressService = new MacAddressService();
	// CommandExecutor
	protected List<ACommandInterceptor> customPreCommandInterceptors;
	protected List<ACommandInterceptor> customPostCommandInterceptors;
	protected List<ACommandInterceptor> commandInterceptors;
	protected ICommandExecutor commandExecutor;
	protected ACommandInterceptor actualCommandExecutor = new CommandExecutor();
	// SessionFactory
	protected ISessionFactory sessionFactory;
	// TransactionFactory
	ITransactionFactory transactionFactory;
	// MacGenerator
	protected IMacGenerator macGenerator;

	public IMacAddressService getMacAddressService() {
		return macAddressService;
	}

	public AConfiguration setMacAddressService(IMacAddressService macAddressService) {
		this.macAddressService = macAddressService;
		return this;
	}

	public List<ACommandInterceptor> getCustomPreCommandInterceptors() {
		return customPreCommandInterceptors;
	}

	public AConfiguration setCustomPreCommandInterceptors(
			List<ACommandInterceptor> customPreCommandInterceptors) {
		this.customPreCommandInterceptors = customPreCommandInterceptors;
		return this;
	}

	public List<ACommandInterceptor> getCustomPostCommandInterceptors() {
		return customPostCommandInterceptors;
	}

	public AConfiguration setCustomPostCommandInterceptors(
			List<ACommandInterceptor> customPostCommandInterceptors) {
		this.customPostCommandInterceptors = customPostCommandInterceptors;
		return this;
	}

	public List<ACommandInterceptor> getCommandInterceptors() {
		return commandInterceptors;
	}

	public AConfiguration setCommandInterceptors(List<ACommandInterceptor> commandInterceptors) {
		this.commandInterceptors = commandInterceptors;
		return this;
	}

	public ICommandExecutor getCommandExecutor() {
		return commandExecutor;
	}

	public AConfiguration setCommandExecutor(ICommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
		return this;
	}

	public ICommandExecutor getActualCommandExecutor() {
		return actualCommandExecutor;
	}

	public AConfiguration setActualCommandExecutor(ACommandInterceptor actualCommandExecutor) {
		this.actualCommandExecutor = actualCommandExecutor;
		return this;
	}

	public ISessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public AConfiguration setSessionFactory(ISessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		return this;
	}

	public ITransactionFactory getTransactionFactory() {
		return transactionFactory;
	}

	public AConfiguration setTransactionFactory(ITransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
		return this;
	}

	public IMacGenerator getMacGenerator() {
		return macGenerator;
	}

	public AConfiguration setMacGenerator(IMacGenerator macGenerator) {
		this.macGenerator = macGenerator;
		return this;
	}

	protected abstract Collection<? extends ACommandInterceptor> getDefaultCommandInterceptors();

	public abstract IEngine buildEngine();
}
