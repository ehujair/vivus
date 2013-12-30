package org.vivus.nda.context;

public interface Command<T> {
	T execute(CommandContext commandContext);
}
