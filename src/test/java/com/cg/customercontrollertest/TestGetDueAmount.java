package com.cg.customercontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bean.Statement;
import com.cg.controller.CustomerController;
import com.cg.service.IStatementService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestGetDueAmount {

	@InjectMocks
	CustomerController customerController;

	@Mock
	IStatementService statementService;

	@DisplayName("Get Due Amount with valid data")
	@Test
	final void testGetDueAmountValid() {

		// Mock Statement Service
		when(statementService.getStatementByIdAndCardNumber(26, "1234 5678 1234")).thenReturn(new Statement());

		// Get Statement
		Statement statement = customerController.getDueAmount(26, "1234 5678 1234");

		// Check test
		assertThat(statement).isNotNull();

	}

	@DisplayName("Get Due Amount with invalid data")
	@Test
	final void testGetDueAmountInvalid() {

		// Mock Statement Service
		when(statementService.getStatementByIdAndCardNumber(-1L, "")).thenReturn(null);

		// Get Statement
		Statement statement = customerController.getDueAmount(-1L, "");

		// Check test
		assertThat(statement).isNull();

	}

}
