package com.cg.admincontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.controller.AdminController;
import com.cg.service.ICustomerService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestRemoveCustomer {

	@InjectMocks
	AdminController adminController;

	@Mock
	ICustomerService custService;

	@DisplayName("Remove customer with valid data")
	@Test
	final void testRemoveCustomerValid() {

		when(custService.removeCustomer(1)).thenReturn("Customer record deleted");

		assertThat(adminController.removeCustomer(1)).isEqualTo("Customer record deleted");
	}

	@DisplayName("Remove customer with invalid data")
	@Test
	final void testRemoveCustomerInvalid() {

		when(custService.removeCustomer(-1)).thenReturn("Customer does not exist!");

		assertThat(adminController.removeCustomer(-1)).isEqualTo("Customer does not exist!");
	}

}
