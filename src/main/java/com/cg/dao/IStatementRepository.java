package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bean.Statement;

@Repository
public interface IStatementRepository extends JpaRepository<Statement, Long> {

	// Get Statment for a customer
	@Query(value = "SELECT s FROM Statement s WHERE s.customer.id = ?1 AND s.statementId = ?2")
	public Statement findStatementByIdAndStatementId(long id, long statementId);

	// Get all statements for a customer
	@Query(value = "SELECT s FROM Statement s WHERE s.customer.id = ?1")
	public List<Statement> findAllStatementsById(long id);

	// Get latest billing date for a customer
	@Query(value = "SELECT MAX(s.billingDate) FROM Statement s WHERE s.customer.id = ?1 AND s.cardNumber = ?2")
	public LocalDate findLatestBillingDate(long id, String cardNumber);

	// Get Statement from statment from cardNumber
	@Query(value = "SELECT s FROM Statement s WHERE s.customer.id = ?1 AND s.cardNumber = ?2 AND s.billingDate = ?3")
	public Statement findStatementByIdAndCardNumber(long id, String cardNumber, LocalDate latestBillingDate);

	// Get All Statement for a card
	@Query(value = "SELECT s FROM Statement s WHERE s.customer.id = ?1 AND s.cardNumber = ?2")
	public List<Statement> findStatementsByIdAndCardNumber(long id, String cardNumber);

}
