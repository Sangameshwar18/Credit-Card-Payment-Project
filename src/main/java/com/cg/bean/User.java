package com.cg.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userDetails")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	// Instance Variables
	@NotNull(message = "Id can't be null")
	@Id
	@GeneratedValue
	private long id;

	@NotNull(message = "User ID can't be null")
	@NotBlank(message = "User ID can't be blank")
	protected String userId;

	@NotNull(message = "Password can't be null")
	@NotBlank(message = "Password can't be blank")
	@Column(length = 50, nullable = false)
	protected String password;

	@NotNull(message = "User role can't be null")
	@NotBlank(message = "User role can't be blank")
	protected String role;

	// Constructors
	public User() {
	}

	public User(@NotNull(message = "Id can't be null") Long id,
			@NotNull(message = "User Id can't be null") @NotBlank(message = "User Id can't be blank") String userId,
			@NotNull(message = "password can't be null") @NotBlank(message = "password can't be blank") String password,
			@NotNull(message = "User role can't be null") @NotBlank(message = "User role can't be blank") String role) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	public User(@NotNull(message = "User Id can't be null") @NotBlank(message = "User Id can't be blank") String userId,
			@NotNull(message = "password can't be null") @NotBlank(message = "password can't be blank") String password,
			@NotNull(message = "User role can't be null") @NotBlank(message = "User role can't be blank") String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	// Getters and Setters

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Methods

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}

}
