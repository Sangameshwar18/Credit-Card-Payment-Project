package com.cg.admincontrollertest;

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

import com.cg.bean.CreditCard;
import com.cg.controller.AdminController;
import com.cg.service.ICreditCardService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestAddCreditCard {

	@InjectMocks
	AdminController adminController;

	@Mock
	ICreditCardService creditCardService;

	@DisplayName("Add Credit card valid data")
	@Test
	final void testAddCreditCardValid() {

		CreditCard creditCard = new CreditCard("CardName", "CardType", "CardNumber", LocalDate.now(), "BankName");

		when(creditCardService.addCreditCard(1, creditCard))
				.thenReturn(new ResponseEntity<String>("Card added successfully", HttpStatus.OK));

		ResponseEntity<String> response = adminController.addCreditCard(1, creditCard);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Card added successfully");
	}

	@DisplayName("Add Credit card invalid data")
	@Test
	final void testAddCreditCardInvalid() {

		when(creditCardService.addCreditCard(-1, null)).thenReturn(new ResponseEntity<String>(
				"Customer with id " + -1 + " doesn't exist. \nEnter a valid customer id.", HttpStatus.NOT_FOUND));

		ResponseEntity<String> response = adminController.addCreditCard(-1, null);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody())
				.isEqualTo("Customer with id " + -1 + " doesn't exist. \nEnter a valid customer id.");
	}

}
