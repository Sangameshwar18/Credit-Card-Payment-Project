package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bean.CreditCard;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCard, Long> {

	// Find Credit Card for a customer
	@Query(value = "SELECT card FROM CreditCard card WHERE card.customer.id = ?1 AND card.cardId = ?2")
	public CreditCard findCreditCardByIdAndCardId(long id, long cardId);

	// Find All Credit Cards for a customer
	@Query(value = "SELECT card FROM CreditCard card WHERE card.customer.id = ?1")
	public List<CreditCard> findAllCreditCardById(long id);

	// Find Credit Card By Card Number
	public CreditCard findByCardNumber(String cardNumber);
}
