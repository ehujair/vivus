package org.vivus.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class DefaultBusinessExceptionDescriptor implements BusinessExceptionDescriptor {
	ResourceBundle bundle;

	public DefaultBusinessExceptionDescriptor(String resourceBaseName) {
		bundle = ResourceBundle.getBundle(resourceBaseName);
	}

	public DefaultBusinessExceptionDescriptor(String resourceBaseName, Locale locale) {
		bundle = ResourceBundle.getBundle(resourceBaseName, locale);
	}

	public String getDescription(String code) {
		return bundle.getString(code);
	}

}
