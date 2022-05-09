package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.CreditCard;
import com.cg.service.ICreditCardService;

@RestController
public class CreditCardController {

	@Autowired
	private ICreditCardService cardService;

	// Add Credit Card to customer
	@PostMapping("/addCreditCard/{customerId}")
	public ResponseEntity<String> addCreditCard(@PathVariable("customerId") long custId,
			@RequestBody CreditCard creditCard) {

		return cardService.addCreditCard(custId, creditCard);
	}

	// Delete Credit Card for a customer
	@DeleteMapping("/removeCard/{id}/{cardId}")
	public ResponseEntity<String> removeCard(@PathVariable("id") long id, @PathVariable("cardId") long cardId) {

		return cardService.removeCreditCard(id, cardId);
	}

	// Update Credit Card
	@PutMapping("/updateCard/{id}/{cardId}")
	public CreditCard updateCard(@PathVariable("id") long id, @PathVariable("cardId") long cardId,
			@RequestBody CreditCard creditCard) {

		return cardService.updateCreditCard(id, cardId, creditCard);
	}

	// Get Credit Card
	@GetMapping("/getCreditCard/{id}/{cardId}")
	public CreditCard getCard(@PathVariable("id") long id, @PathVariable("cardId") long cardId) {

		return cardService.getCreditCard(id, cardId);
	}

	// Get All Credit Cards for a customer
	@GetMapping("/getAllCreditCards/{id}")
	public List<CreditCard> getAllCards(@PathVariable("id") long id) {

		return cardService.getAllCreditCards(id);
	}

}
