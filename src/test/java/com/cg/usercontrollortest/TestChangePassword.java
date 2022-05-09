package com.cg.usercontrollortest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.LoginController;
import com.cg.service.IUserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestChangePassword {

	@InjectMocks
	LoginController loginController;

	@Mock
	IUserService userService;

	@DisplayName("Admin password change with valid Id")
	@Test
	final void testUserChangePwd() {

		// Mock the user Service
		when(userService.changePassword(1L, "admin@1"))
				.thenReturn(new ResponseEntity<String>("Password Changed Successfully", HttpStatus.OK));

		// Get Response
		ResponseEntity<String> response = loginController.changePassword(1L, "admin@1");

		// Check Test
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Password Changed Successfully", response.getBody());

	}

	@DisplayName("User password change with invalid Id")
	@Test
	final void testUserChangePwdWithIncId() {

		// Mock the user Service
		when(userService.changePassword(-1L, "admin@1"))
				.thenReturn(new ResponseEntity<String>("User with id " + -1L + " doesn't exist", HttpStatus.NOT_FOUND));

		// Get Response
		ResponseEntity<String> response = loginController.changePassword(-1L, "admin@1");

		// Check Test
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User with id " + -1L + " doesn't exist", response.getBody());

	}

}
