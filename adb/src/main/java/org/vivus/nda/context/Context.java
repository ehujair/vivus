package org.vivus.nda.context;

import java.util.Stack;

public class Context {
	protected static ThreadLocal<Stack<CommandContext>> commandContextThreadLocal = new ThreadLocal<Stack<CommandContext>>();

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

	protected static <T> Stack<T> getStack(ThreadLocal<Stack<T>> threadLocal) {
		Stack<T> stack = threadLocal.get();
		if (stack == null) {
			stack = new Stack<T>();
			threadLocal.set(stack);
		}
		return stack;
	}
}
