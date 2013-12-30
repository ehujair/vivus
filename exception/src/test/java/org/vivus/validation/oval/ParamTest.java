package org.vivus.validation.oval;

import junit.framework.Assert;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.context.MethodParameterContext;
import net.sf.oval.exception.ConstraintsViolatedException;
import net.sf.oval.guard.Guard;
import net.sf.oval.guard.Guarded;

import org.junit.Test;

public class ParamTest {
	@Guarded
	private class User {
		void say(@NotNull String name) {
			System.out.println("Hello, " + name);
		}
	}

	@Test
	public void test() {
		final Guard guard = new Guard();
		OvalTestGuardAspect.aspectOf().setGuard(guard);
		try {
			User user = new User();
			user.say(null);
		} catch (ConstraintsViolatedException e) {
			ConstraintViolation[] violations = e.getConstraintViolations();
			for (ConstraintViolation violation : violations) {
				System.out.println(violation);
			}
			Assert.assertNotNull(violations);
			Assert.assertEquals(1, violations.length);
			Assert.assertTrue(violations[0].getContext() instanceof MethodParameterContext);
		}
	}

}
