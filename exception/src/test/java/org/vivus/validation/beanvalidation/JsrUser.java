package org.vivus.validation.beanvalidation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class JsrUser {
	@NotNull
	@Size(max = 64)
	String id;
	@Size(max = 100)
	String name;
	@NotNull
	@Size(min = 8, max = 64)
	String password;
	@Email
	String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(@NotNull String email) {
		this.email = email;
	}

	@NotNull
	public String sayHello() {
		if (name == null)
			return null;
		if (name.length() < 1)
			return "";
		return "Hello, " + name;
	}

	public void print(@NotNull String str) {
		System.out.println(str);
	}
}
