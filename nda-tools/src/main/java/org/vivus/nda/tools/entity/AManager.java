package org.vivus.nda.tools.entity;

import org.vivus.nda.tools.context.Context;
import org.vivus.nda.tools.persist.ISession;

public abstract class AManager {

	public ISession getSession() {
		return Context.peekCommandContext().getSession();
	}

}
