package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bean.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

	// Get Transaction for the customer
	@Query(value = "SELECT t FROM Transaction t WHERE t.customer.id = ?1 AND transactionId = ?2")
	public Transaction findTransactionByIdAndTransactionId(long id, long transactionId);

	// Get All Transaction for the customer
	@Query(value = "SELECT t FROM Transaction t WHERE t.customer.id = ?1")
	public List<Transaction> findAllTransactionById(long id);

	// Get All Sum of all amount for a card between two dates
	@Query(value = "SELECT SUM(t.payment.amountPaid) FROM Transaction t WHERE t.cardNumber = ?1 AND t.transactionDate BETWEEN ?2 AND ?3")
	public Double totalAmountByCard(String cardNumber, LocalDate startDate, LocalDate endDate);

}
