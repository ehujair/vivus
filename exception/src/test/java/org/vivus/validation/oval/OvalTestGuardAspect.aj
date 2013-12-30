package org.vivus.validation.oval;

import net.sf.oval.guard.GuardAspect;
import net.sf.oval.guard.Guarded;

public aspect OvalTestGuardAspect extends GuardAspect {
	protected pointcut scope(): @within(Guarded);
}
