package org.vivus.exception;

import org.junit.Test;

public class BusinessExceptionUsageTest {
	@Test(expected = BusinessException.class)
	public void test() throws BusinessException {
		BusinessException businessException = new BusinessException(
				BusinessExceptionConstants.LOGIN_NO_SUCH_USER);
		String code = businessException.getCode();
		System.out.println("The exception code: " + code);
		BusinessExceptionDescriptor descriptor = BusinessExceptionDescriptorFactory
				.getDefaultBusinessExceptionDescriptor();
		System.out.println("The exception description: " + descriptor.getDescription(code));
		throw businessException;
	}

	@Test(expected = BusinessException.class)
	public void test1() throws BusinessException {
		BusinessException businessException = new BusinessException(
				BusinessExceptionConstants.LOGIN_NO_SUCH_USER);
		System.out.println("The exception code: " + businessException.getCode());
		System.out.println("The exception description: " + businessException.getDescription());
		throw businessException;
	}

	@Test(expected = BusinessException.class)
	public void test2() throws BusinessException {
		throw new BusinessException(BusinessExceptionConstants.LOGIN_NO_SUCH_USER);
	}
}
