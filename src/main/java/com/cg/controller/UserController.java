package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.User;
import com.cg.service.IUserService;

@RestController
@RequestMapping("/home")
public class UserController {

	@Autowired
	IUserService userService;

	// add User
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User newUser) {

		return userService.addUser(newUser);
	}

	// remove User
	@DeleteMapping("/removeUser/{id}")
	public ResponseEntity<String> removeUser(@PathVariable("id") long id) {

		return userService.removeUser(id);
	}

}
