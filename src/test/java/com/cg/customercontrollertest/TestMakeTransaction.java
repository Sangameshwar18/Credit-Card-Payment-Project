package com.cg.customercontrollertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bean.Payment;
import com.cg.bean.Transaction;
import com.cg.controller.CustomerController;
import com.cg.service.ITransactionService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestMakeTransaction {

	@InjectMocks
	CustomerController customerController;

	@Mock
	ITransactionService transactionService;

	@DisplayName("Make transaction with valid data")
	@Test
	final void testMakeTransactionValid() {

		// Mock Transaction service
		Transaction transaction = new Transaction("1234 3456 7890", LocalDate.now(), "Successfull",
				new Payment("UPI", 1200.0), "Shopping");

		when(transactionService.addTransaction(1, transaction))
				.thenReturn(new ResponseEntity<Object>("Transaction added", HttpStatus.ACCEPTED));

		// Get Response
		ResponseEntity<Object> response = customerController.makeTransaction(1, transaction);

		// test response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		assertThat(response.getBody()).isEqualTo("Transaction added");

	}

	@DisplayName("Make transaction with invalid data")
	@Test
	final void testMakeTransactionInvalid() {

		// Mock Transaction service
		when(transactionService.addTransaction(-1, null)).thenReturn(new ResponseEntity<Object>(
				"Customer with id " + -1 + " doesn't exist. \nEnter a valid customer id.", HttpStatus.NOT_FOUND));

		// Get Response
		ResponseEntity<Object> response = customerController.makeTransaction(-1, null);

		// test response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody())
				.isEqualTo("Customer with id " + -1 + " doesn't exist. \nEnter a valid customer id.");

	}

}
