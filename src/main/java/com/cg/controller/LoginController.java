package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.service.IUserService;

@RestController
@RequestMapping("/home")
public class LoginController {

	@Autowired
	private IUserService userService;

	// sign in
	@GetMapping("/signIn/{userId}/{password}")
	public ResponseEntity<Object> signIn(@PathVariable("userId") String userId,
			@PathVariable("password") String password) {

		return userService.signIn(userId, password);
	}

	// sign out
	@GetMapping("/signOut")
	public ResponseEntity<String> signOut() {

		return userService.signOut();
	}

	// change password
	@PatchMapping("/changePwd/{id}/{newPassword}")
	public ResponseEntity<String> changePassword(@PathVariable("id") Long id,
			@PathVariable("newPassword") String newPassword) {

		return userService.changePassword(id, newPassword);
	}
}
