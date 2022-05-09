package com.cg.usercontrollortest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.LoginController;
import com.cg.service.IUserService;

@SpringBootTest
class TestLogin {

	@InjectMocks
	LoginController loginController;

	@Mock
	IUserService userService;

	@DisplayName("Sign in with invalid data")
	@Test
	public void testSignIn1() {
		when(userService.signIn("", "")).thenReturn(new ResponseEntity<Object>("Values cannot be null", HttpStatus.OK));

		ResponseEntity<Object> response = loginController.signIn("", "");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Values cannot be null", response.getBody());
	}

	@DisplayName("Sign in with valid data")
	@Test
	public void testSignIn2() {
		when(userService.signIn("user1", "abcd@123"))
				.thenReturn(new ResponseEntity<Object>("User Signed In", HttpStatus.OK));

		ResponseEntity<Object> response = loginController.signIn("user1", "abcd@123");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("User Signed In", response.getBody());
	}

	@DisplayName("Sign out")
	@Test
	public void testSignout() {
		// Mock the user service class
		when(userService.signOut()).thenReturn(new ResponseEntity<String>("Signed Out", HttpStatus.OK));

		// Getting response
		ResponseEntity<String> response = loginController.signOut();

		// Checking test case
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Signed Out", response.getBody());
	}

}
