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

import com.cg.bean.Statement;
import com.cg.controller.CustomerController;
import com.cg.service.ICustomerService;
import com.cg.service.IStatementService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestGetStatement {

	@InjectMocks
	CustomerController customerController;

	@Mock
	IStatementService statementService;

	@Mock
	ICustomerService custService;

	@DisplayName("Get statement with valid data")
	@Test
	final void testGetStatementWithValid() {

		// Mock Statement service
		when(statementService.getStatementByIdAndStatementId(1, 3)).thenReturn(new Statement());

		// Get Statement
		Statement statement = customerController.getStatementByIdAndStatementId(1, 3);

		// test statement
		assertThat(statement).isNotNull();
	}

	@DisplayName("Get statement with invalid data")
	@Test
	final void testGetStatementWithInvalid() {

		// Mock Statement service
		when(statementService.getStatementByIdAndStatementId(-1, -3)).thenReturn(null);

		// Get Statement
		Statement statement = customerController.getStatementByIdAndStatementId(-1, -3);

		// test statement
		assertThat(statement).isNull();
	}

	@DisplayName("Get statement with valid credit card data")
	@Test
	final void testGetStatementByCCWithValid() {

		// Mock Statement service
		when(statementService.getStatementByIdAndCardNumber(1, "1234 1245 5647")).thenReturn(new Statement());

		// Get Statement
		Statement statement = customerController.getStatementByIdAndCardNumber(1, "1234 1245 5647");

		// test statement
		assertThat(statement).isNotNull();
	}

	@DisplayName("Get statement with invalid credit card data")
	@Test
	final void testGetStatementByCCWithInalid() {

		// Mock Statement service
		when(statementService.getStatementByIdAndCardNumber(-1, "")).thenReturn(null);

		// Get Statement
		Statement statement = customerController.getStatementByIdAndCardNumber(-1, "");

		// test statement
		assertThat(statement).isNull();
	}

	@DisplayName("Get all statements with valid credit card data")
	@Test
	final void testGetStatementsByCCWithValid() {

		// Mock Statement service
		when(statementService.getStatementsByIdAndCardNumber(1, "1234 1245 5647"))
				.thenReturn(new ArrayList<Statement>());

		// Get Statement
		List<Statement> statements = customerController.getAllStatementsByIdAndCardNumber(1, "1234 1245 5647");

		// test statement
		assertThat(statements).isNotNull();

	}

	@DisplayName("Get all statements with invalid credit card data")
	@Test
	final void testGetStatementsByCCWithInvalid() {

		// Mock Statement service
		when(statementService.getStatementsByIdAndCardNumber(1, "1234 1245 5647")).thenReturn(null);

		// Get Statement
		List<Statement> statements = customerController.getAllStatementsByIdAndCardNumber(1, "1234 1245 5647");

		// test statement
		assertThat(statements).isNull();

	}

	@DisplayName("Get statement history with valid id")
	@Test
	final void testGetStatementHistoryWithValidId() {

		// Mock Customer service
		when(custService.getStatementHistory(1)).thenReturn(new ArrayList<Statement>());

		// Get Statement
		List<Statement> statements = customerController.getStatementHistory(1);

		// test statement
		assertThat(statements).isNotNull();

	}

	@DisplayName("Get statement history with invalid id")
	@Test
	final void testGetStatementHistoryWithInvalidId() {

		// Mock Customer service
		when(custService.getStatementHistory(-1)).thenReturn(null);

		// Get Statement
		List<Statement> statements = customerController.getStatementHistory(-1);

		// test statement
		assertThat(statements).isNull();

	}

}
