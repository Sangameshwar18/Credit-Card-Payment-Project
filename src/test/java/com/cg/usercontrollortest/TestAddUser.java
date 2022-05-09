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

import com.cg.bean.User;
import com.cg.controller.UserController;
import com.cg.service.IUserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestAddUser {

	@InjectMocks
	UserController userController;

	@Mock
	IUserService userService;

	@DisplayName("Adding admin")
	@Test
	final void testAddAdmin() {

		User admin = new User("admin1", "admin@1", "ADMIN");

		// Mock the User Service class
		when(userService.addUser(admin))
				.thenReturn(new ResponseEntity<String>("User added Successfully", HttpStatus.OK));

		// Get the response
		ResponseEntity<String> response = userController.addUser(admin);

		// Check the test
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("User added Successfully", response.getBody());
	}

	@DisplayName("Adding Customer")
	@Test
	final void testAddCustomer() {

		User customer = new User("user1", "user@1", "CUST");

		// Mock the User Controller
		when(userService.addUser(customer)).thenReturn(new ResponseEntity<String>(
				"Customer can't create direct account contact to admin", HttpStatus.NOT_ACCEPTABLE));

		// Get the response
		ResponseEntity<String> response = userController.addUser(customer);

		// Check the test
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
		assertEquals("Customer can't create direct account contact to admin", response.getBody());

	}

}
