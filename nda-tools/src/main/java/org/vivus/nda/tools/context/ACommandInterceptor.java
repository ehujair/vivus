package org.vivus.nda.tools.context;

public abstract class ACommandInterceptor implements ICommandExecutor {
	ICommandExecutor next;

	public ICommandExecutor getNext() {
		return next;
	}

	public void setNext(ICommandExecutor next) {
		this.next = next;
	}

	public <T> T execute(ICommand<T> command) {
		try {
			before(command);
			T t = next.execute(command);
			after(command);
			return t;
		} finally {
			finnaly(command);
		}
	}

	public <T> void before(ICommand<T> command) {
	}

	public <T> void after(ICommand<T> command) {
	}

	public <T> void finnaly(ICommand<T> command) {
	}
}
