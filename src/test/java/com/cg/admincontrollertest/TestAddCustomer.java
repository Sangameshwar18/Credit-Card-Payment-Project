package com.cg.admincontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bean.Address;
import com.cg.bean.Customer;
import com.cg.controller.AdminController;
import com.cg.service.ICustomerService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestAddCustomer {

	@InjectMocks
	AdminController adminController;

	@Mock
	ICustomerService custService;

	@DisplayName("Add user with valid data")
	@Test
	final void testAddUserValid() {

		// Mock Customer service
		List<Address> address = new ArrayList<Address>();

		Customer customer = new Customer("cust1", "cust@1", "CUST", "customer", "cust1@gmail.com", "123243554",
				LocalDate.now().minusYears(20), address);

		when(custService.addCustomer(customer))
				.thenReturn(new ResponseEntity<String>("Customer added successfully", HttpStatus.ACCEPTED));

		// Get Response
		ResponseEntity<String> response = adminController.addCustomer(customer);

		// test Response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		assertThat(response.getBody()).isEqualTo("Customer added successfully");
	}

	@DisplayName("Add user with invalid data")
	@Test
	final void testAddUserInvalid() {

		// Mock Customer service
		when(custService.addCustomer(null))
				.thenReturn(new ResponseEntity<String>("Invalid Data", HttpStatus.NOT_ACCEPTABLE));

		// Get Response
		ResponseEntity<String> response = adminController.addCustomer(null);

		// test Response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
		assertThat(response.getBody()).isEqualTo("Invalid Data");
	}

}
