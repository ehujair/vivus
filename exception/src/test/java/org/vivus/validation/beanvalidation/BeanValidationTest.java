package org.vivus.validation.beanvalidation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.Assert;

import org.junit.Test;

public class BeanValidationTest {
	@Test
	public void test() {
		JsrUser user = new JsrUser();
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<JsrUser>> validates = validator.validate(user);
		for (ConstraintViolation<JsrUser> validate : validates) {
			System.out.println(validate);
		}
		Assert.assertNotNull(validates);
		Assert.assertTrue(validates.size() > 0);
	}

	@Test
	public void testParam() {
		JsrUser user = new JsrUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.setEmail(null);
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<JsrUser>> validates = validator.validate(user);
		for (ConstraintViolation<JsrUser> validate : validates) {
			System.out.println(validate);
		}
		Assert.assertNotNull(validates);
		Assert.assertTrue(validates.size() > 0);
	}
	
	@Test
	public void testParam2() {
		JsrUser user = new JsrUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.print(null);
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<JsrUser>> validates = validator.validate(user);
		for (ConstraintViolation<JsrUser> validate : validates) {
			System.out.println(validate);
		}
		Assert.assertNotNull(validates);
		Assert.assertTrue(validates.size() > 0);
	}

	@Test
	public void testSize() {
		JsrUser user = new JsrUser();
		user.setId("testParam");
		user.setPassword("pwd");
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<JsrUser>> validates = validator.validate(user);
		for (ConstraintViolation<JsrUser> validate : validates) {
			System.out.println(validate);
		}
		Assert.assertNotNull(validates);
		Assert.assertTrue(validates.size() > 0);
	}

	@Test
	public void testMethod() {
		JsrUser user = new JsrUser();
		user.setId("testParam");
		user.setPassword("param-pwd");
		user.sayHello();
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<JsrUser>> validates = validator.validate(user);
		for (ConstraintViolation<JsrUser> validate : validates) {
			System.out.println(validate);
		}
		Assert.assertNotNull(validates);
		Assert.assertTrue(validates.size() > 0);
	}
}
