package org.vivus.validation.oval;

import net.sf.oval.configuration.annotation.IsInvariant;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

@Guarded
public class OvalUser {
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
	String address;

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

	public void setEmail(String email) {
		this.email = email;
	}

	@IsInvariant
	@NotNull
	public String getAddress() {
		return address;
	}

	public void setAddress(@NotNull String address) {
		this.address = address;
	}

	@IsInvariant
	@NotNull
	public String sayHello(String msg) {
		if (name == null)
			return null;
		if (name.length() < 1)
			return "";
		return "Hello, " + name + "." + msg;
	}

	public void print(@NotNull String str) {
		System.out.println(str);
	}
}
