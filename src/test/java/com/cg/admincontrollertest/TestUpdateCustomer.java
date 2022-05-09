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

import com.cg.bean.Address;
import com.cg.bean.Customer;
import com.cg.controller.AdminController;
import com.cg.service.ICustomerService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestUpdateCustomer {

	@InjectMocks
	AdminController adminController;

	@Mock
	ICustomerService custService;

	@DisplayName("Update customer with valid data")
	@Test
	final void testUpdateCustomerValid() {

		List<Address> address = new ArrayList<Address>();

		Customer customer = new Customer("cust1", "cust@1", "CUST", "customer", "cust1@gmail.com", "123243554",
				LocalDate.now().minusYears(20), address);

		when(custService.updateCustomer(1, customer)).thenReturn("Record Updated Successfully");

		assertThat(adminController.updateCustomer(1, customer)).isEqualTo("Record Updated Successfully");
	}

	@DisplayName("Update customer with invalid data")
	@Test
	final void testUpdateCustomerInvalid() {

		List<Address> address = new ArrayList<Address>();

		Customer customer = new Customer("cust1", "cust@1", "CUST", "customer", "cust1@gmail.com", "123243554",
				LocalDate.now().minusYears(20), address);

		when(custService.updateCustomer(-1, customer)).thenReturn("Customer doesn't exist");

		assertThat(adminController.updateCustomer(-1, customer)).isEqualTo("Customer doesn't exist");
	}

}
