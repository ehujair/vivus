package org.vivus.nda.tools.context;

import org.vivus.nda.tools.persist.ITransaction;

public interface ITransactionFactory {
	ITransaction openTransaction(CommandContext commandContext);
}
