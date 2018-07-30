package com.github.bjconlan.pccwglobaldemo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * A domain object representing the User data adhering to the swagger definitions
 * for a User.
 *
 * *NOTE* in this implementation the domain object is also used to represents the
 *        swagger definitions for CreateUserRequest and UpdateUserRequest which
 *        is possible due to the common field requirements and constraints.
 *
 * @since 0.0.1
 */
@Entity
public class User {
	@Id
	@GenericGenerator(name="uuid", strategy = "uuid") // Not ideal (as its not JPA but hibernate specific)
	@GeneratedValue(generator = "uuid")
	private String id;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String password;

	@NotNull
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
