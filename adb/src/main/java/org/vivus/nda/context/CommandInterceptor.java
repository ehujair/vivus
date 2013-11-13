package org.vivus.nda.context;

public abstract class CommandInterceptor implements CommandExecutor {
	CommandExecutor next;

	public CommandExecutor getNext() {
		return next;
	}

	public void setNext(CommandExecutor next) {
		this.next = next;
	}

	public <T> T execute(Command<T> command) {
		try {
			before(command);
			T t = next.execute(command);
			after(command);
			return t;
		} finally {
			finnaly(command);
		}
	}

	public <T> void before(Command<T> command) {
	}

	public <T> void after(Command<T> command) {
	}

	public <T> void finnaly(Command<T> command) {
	}
}
