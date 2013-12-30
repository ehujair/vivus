package org.vivus.nda.tools.persist.mybatis;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ITransactionFactory;
import org.vivus.nda.tools.persist.ITransaction;

public class MybatisTransactionFactory implements ITransactionFactory {

	@Override
	public ITransaction openTransaction(CommandContext commandContext) {
		return (ITransaction) commandContext.getSession();
	}

}
