package org.vivus.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = -1324691858974243527L;

	private String code;
	BusinessExceptionDescriptor descriptor = BusinessExceptionDescriptorFactory
			.getDefaultBusinessExceptionDescriptor();

	public BusinessException(String code) {
		super(BusinessExceptionDescriptorFactory.getDefaultBusinessExceptionDescriptor()
				.getDescription(code));
		this.code = code;
	}

	public BusinessException(String code, BusinessExceptionDescriptor descriptor) {
		super(descriptor.getDescription(code));
		this.code = code;
		this.descriptor = descriptor;
	}

	public BusinessException(String code, Throwable cause) {
		super(BusinessExceptionDescriptorFactory.getDefaultBusinessExceptionDescriptor()
				.getDescription(code), cause);
		this.code = code;
	}

	public BusinessException(String code, BusinessExceptionDescriptor descriptor, Throwable cause) {
		super(descriptor.getDescription(code), cause);
		this.code = code;
		this.descriptor = descriptor;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return descriptor.getDescription(code);
	}
}
