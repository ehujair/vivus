package org.vivus.nda.tools.context;

public interface ICommand<T> {
	T execute(CommandContext commandContext);
}
