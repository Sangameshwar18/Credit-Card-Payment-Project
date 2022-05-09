package com.cg.bean;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

	public Admin() {
	}

	public Admin(User user) {
		super(user.getUserId(), user.getPassword(), user.getRole());
	}
}
