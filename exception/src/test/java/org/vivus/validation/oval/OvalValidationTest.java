package org.vivus.validation.oval;

import java.util.List;

import junit.framework.Assert;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.junit.Test;

public class OvalValidationTest {
	@Test
	public void test() {
		OvalUser user = new OvalUser();
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}

	@Test
	public void testSize() {
		OvalUser user = new OvalUser();
		user.setId("testParam");
		user.setPassword("pwd");
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}

	@Test
	public void testGetterMethod() {
		OvalUser user = new OvalUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}

	@Test
	public void testGetterMethod2() {
		OvalUser user = new OvalUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.getAddress();
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}

	@Test
	public void testParam() {
		OvalUser user = new OvalUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.setAddress(null);
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}

	@Test
	public void testParam2() {
		OvalUser user = new OvalUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.print(null);
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}

	@Test
	public void testMethod() {
		OvalUser user = new OvalUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.sayHello("Welcome to China!");
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(user);
		for (ConstraintViolation violation : violations) {
			System.out.println(violation);
		}
		Assert.assertNotNull(violations);
		Assert.assertTrue(violations.size() > 0);
	}
}
