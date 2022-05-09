package com.cg.customercontrollertest;

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

import com.cg.bean.Payment;
import com.cg.controller.CustomerController;
import com.cg.service.ICustomerService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestGetPaymentHisory {

	@InjectMocks
	CustomerController customerController;

	@Mock
	ICustomerService custService;

	@DisplayName("Get Payment History with valid data")
	@Test
	final void testGetPaymentHistoryValid() {

		// mock Customer Service
		when(custService.getPaymentHistory(1, 3)).thenReturn(new ArrayList<Payment>());

		// Get Payment History
		List<Payment> payments = customerController.getPaymentHistory(1, 3);

		// Test Payment History
		assertThat(payments).isNotNull();
	}

	@DisplayName("Get Payment History with invalid data")
	@Test
	final void testGetPaymentHistoryInvalid() {

		// mock Customer Service
		when(custService.getPaymentHistory(-1, -3)).thenReturn(null);

		// Get Payment History
		List<Payment> payments = customerController.getPaymentHistory(-1, -3);

		// Test Payment History
		assertThat(payments).isNull();
	}

}
