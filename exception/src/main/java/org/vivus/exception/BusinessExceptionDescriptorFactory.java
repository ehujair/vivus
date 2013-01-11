package org.vivus.exception;

public class BusinessExceptionDescriptorFactory {
	static final String BASE_NAME = "message/message";
	static final BusinessExceptionDescriptor defaultBusinessExceptionDescriptor = new DefaultBusinessExceptionDescriptor(
			BASE_NAME);

	public static BusinessExceptionDescriptor getDefaultBusinessExceptionDescriptor() {
		return defaultBusinessExceptionDescriptor;
	}
}
