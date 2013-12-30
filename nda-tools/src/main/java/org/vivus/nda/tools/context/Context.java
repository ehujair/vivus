package org.vivus.nda.tools.context;

import java.util.Stack;

import org.vivus.nda.tools.config.AConfiguration;

public class Context {
	protected static ThreadLocal<Stack<CommandContext>> commandContextThreadLocal = new ThreadLocal<Stack<CommandContext>>();
	protected static ThreadLocal<Stack<AConfiguration>> configurationThreadLocal = new ThreadLocal<Stack<AConfiguration>>();

	public static CommandContext peekCommandContext() {
		Stack<CommandContext> stack = getStack(commandContextThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void pushCommandContext(CommandContext commandContext) {
		getStack(commandContextThreadLocal).push(commandContext);
	}

	public static void popCommandContext() {
		getStack(commandContextThreadLocal).pop();
	}

	public static AConfiguration peekConfiguration() {
		Stack<AConfiguration> stack = getStack(configurationThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void pushConfiguration(AConfiguration configuration) {
		getStack(configurationThreadLocal).push(configuration);
	}

	public static void popConfiguration() {
		getStack(configurationThreadLocal).pop();
	}

	protected static <T> Stack<T> getStack(ThreadLocal<Stack<T>> threadLocal) {
		Stack<T> stack = threadLocal.get();
		if (stack == null) {
			stack = new Stack<T>();
			threadLocal.set(stack);
		}
		return stack;
	}
}
