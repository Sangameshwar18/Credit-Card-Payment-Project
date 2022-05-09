package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bean.CreditCard;
import com.cg.bean.Customer;
import com.cg.dao.ICreditCardRepository;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.CardNotFoundException;
import com.cg.exception.CustomerNotFoundException;

@Service
public class ICreditCardService {

	@Autowired
	private ICreditCardRepository cardRepository;

	@Autowired
	ICustomerRepository custRepository;

	// add Credit Card to customer
	public ResponseEntity<String> addCreditCard(long id, CreditCard creditCard) {

		if (!custRepository.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		Customer customer = custRepository.findById(id).get();

		creditCard.setCustomer(customer);

		cardRepository.saveAndFlush(creditCard);

		return new ResponseEntity<String>("Card added successfully", HttpStatus.OK);

	}

	// Remove Credit card from customer
	public ResponseEntity<String> removeCreditCard(long id, long cardId) {

		if (!cardRepository.existsById(cardId)) {
			throw new CardNotFoundException("Card with card id " + cardId + " doesn't exists");
		}

		if (!custRepository.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		CreditCard newCard = cardRepository.findCreditCardByIdAndCardId(id, cardId);

		if (newCard == null) {

			throw new CardNotFoundException("Card doesn't exist");
		}

		cardRepository.deleteById(cardId);

		return new ResponseEntity<String>("Card removed successfully", HttpStatus.OK);
	}

	// Update Credit card of the customer
	public CreditCard updateCreditCard(long id, long cardId, CreditCard card) {

		if (!custRepository.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!cardRepository.existsById(cardId)) {
			throw new CardNotFoundException("Card with card id " + cardId + " doesn't exists");
		}

		CreditCard newCard = cardRepository.findCreditCardByIdAndCardId(id, cardId);

		if (newCard == null) {

			throw new CardNotFoundException("Card doesn't exist");
		}

		newCard.setBankName(card.getBankName());
		newCard.setCardNumber(card.getCardNumber());
		newCard.setExpiryDate(card.getExpiryDate());
		newCard.setCardName(card.getCardName());
		newCard.setCardType(card.getCardType());

		cardRepository.saveAndFlush(newCard);

		return newCard;
	}

	// Get a Credit Card for a customer
	public CreditCard getCreditCard(long id, long cardId) {

		if (!custRepository.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!cardRepository.existsById(cardId)) {
			throw new CardNotFoundException("Card with card id " + cardId + " doesn't exists");
		}

		CreditCard card = cardRepository.findCreditCardByIdAndCardId(id, cardId);

		if (card == null) {

			throw new CardNotFoundException("Card doesn't exist");
		}

		return card;
	}

	// Get All Credit Card For a customer
	public List<CreditCard> getAllCreditCards(long id) {

		if (!custRepository.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		return cardRepository.findAllCreditCardById(id);
	}

	// Get Card By Card Number
	public CreditCard getCardByCardNum(String cardNumber) {

		return cardRepository.findByCardNumber(cardNumber);
	}

}
