package com.cg.admincontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bean.Customer;
import com.cg.controller.AdminController;
import com.cg.service.ICustomerService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestGetCustomer {

	@InjectMocks
	AdminController adminController;

	@Mock
	ICustomerService custService;

	@DisplayName("Get customer with valid data")
	@Test
	final void testGetCustomerValid() {

		when(custService.getCustomer(1)).thenReturn(new Customer());

		Customer customer = adminController.getCustomer(1);

		assertThat(customer).isNotNull();
	}

	@DisplayName("Get customer with invalid data")
	@Test
	final void testGetCustomerInvalid() {

		when(custService.getCustomer(-1)).thenReturn(null);

		Customer customer = adminController.getCustomer(-1);

		assertThat(customer).isNull();
	}

	@DisplayName("Get all customers with valid data")
	@Test
	final void testGetCustomersValid() {

		when(custService.getAllCustomers()).thenReturn(new ArrayList<Customer>());

		List<Customer> customers = adminController.getAllCustomers();

		assertThat(customers).isNotNull();
	}

}
