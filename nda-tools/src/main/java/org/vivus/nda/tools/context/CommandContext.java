package org.vivus.nda.tools.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivus.nda.tools.config.AConfiguration;
import org.vivus.nda.tools.entity.AManager;
import org.vivus.nda.tools.entity.FileManager;
import org.vivus.nda.tools.entity.KeyValueManager;
import org.vivus.nda.tools.entity.MacAddressManager;
import org.vivus.nda.tools.file.IPathResolver;
import org.vivus.nda.tools.persist.ISession;
import org.vivus.nda.tools.persist.ISessionFactory;
import org.vivus.nda.tools.persist.ITransaction;

public class CommandContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandContext.class);

	ICommand<?> command;
	AConfiguration configuration;
	ISessionFactory sessionFactory;
	ISession session;
	ITransaction transaction;
	IPathResolver pathResolver;
	Map<Class<? extends AManager>, AManager> managers = new HashMap<Class<? extends AManager>, AManager>();
	Throwable exception;

	public CommandContext(ICommand<?> command, AConfiguration configuration) {
		this.command = command;
		this.configuration = configuration;
		sessionFactory = configuration.getSessionFactory();
		transaction = configuration.getTransactionFactory().openTransaction(this);
		pathResolver = configuration.getPathResolver();
	}

	public AConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(AConfiguration configuration) {
		this.configuration = configuration;
	}

	public ISession getSession() {
		if (session == null) {
			session = getSessionFactory().openSession();
		}
		return session;
	}

	public ISessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new RuntimeException("no SessionFactory configured");
		}
		return sessionFactory;
	}

	public void setSessionFactory(ISessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public IPathResolver getPathResolver() {
		if (pathResolver == null) {
			throw new RuntimeException("no PathResolver configured");
		}
		return pathResolver;
	}

	public void setPathResolver(IPathResolver pathResolver) {
		this.pathResolver = pathResolver;
	}

	@SuppressWarnings("unchecked")
	protected <T extends AManager> T getManager(Class<T> clazz) {
		T manager = (T) managers.get(clazz);
		if (manager == null) {
			try {
				manager = clazz.newInstance();
				managers.put(clazz, manager);
			} catch (InstantiationException e) {
				throw new RuntimeException("can't instantiate manager " + clazz.getName(), e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("illegal access manager " + clazz.getName(), e);
			}
		}
		return manager;
	}

	public MacAddressManager getMacAddressManager() {
		return getManager(MacAddressManager.class);
	}

	public KeyValueManager getKeyValueManager() {
		return getManager(KeyValueManager.class);
	}
	
	public FileManager getFileManager() {
		return getManager(FileManager.class);
	}

	public void close() {
		// the intention of this method is that all resources are closed
		// properly, even if exceptions occur in close or flush methods of the
		// sessions or the transaction context.
		try {
			try {
				try {
					if (exception == null) {
						transaction.commit();
					}
				} catch (Throwable exception) {
					exception(exception);
				}
				if (exception != null) {
					transaction.rollback();
				}
			} catch (Throwable exception) {
				exception(exception);
			} finally {
				getSession().close();
			}
		} catch (Throwable exception) {
			exception(exception);
		}
		// rethrow the original exception if there was one
		if (exception != null) {
			LOGGER.error("", ToStringBuilder.reflectionToString(command));
			if (exception instanceof Error) {
				throw (Error) exception;
			} else if (exception instanceof RuntimeException) {
				throw (RuntimeException) exception;
			} else {
				throw new RuntimeException("exception while executing command " + command,
						exception);
			}
		}
	}

	public void exception(Throwable t) {
		if (this.exception == null) {
			this.exception = t;
		} else {
			LOGGER.error("", t);
		}
	}
}
