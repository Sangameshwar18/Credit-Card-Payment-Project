package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.bean.Statement;
import com.cg.dao.ICustomerRepository;
import com.cg.dao.IStatementRepository;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.NoStatementsFoundException;
import com.cg.exception.StatementNotFoundException;

@Service
public class IStatementService {

	@Autowired
	private IStatementRepository statementRepo;

	@Autowired
	private ICustomerRepository custRepo;

	// Add Statement To customer
	public ResponseEntity<Object> addStatement(long id, Statement newStatement) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		Customer customer = custRepo.findById(id).get();

		newStatement.setCustomer(customer);

		statementRepo.saveAndFlush(newStatement);

		return new ResponseEntity<Object>(newStatement, HttpStatus.OK);
	}

	// Remove Statement from customer
	public ResponseEntity<Object> removeStatement(long id, long statementId) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!statementRepo.existsById(statementId)) {
			throw new StatementNotFoundException("Statement with ID: " + id + " Not Found");
		}

		Statement stmt = statementRepo.findStatementByIdAndStatementId(id, statementId);

		if (stmt == null) {

			throw new StatementNotFoundException("Statement doesn't exist");
		}

		statementRepo.deleteById(statementId);

		return new ResponseEntity<Object>(stmt, HttpStatus.OK);
	}

	// Update Statement
	public Statement UpdateStatement(long id, long statementId, Statement newStatement) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!statementRepo.existsById(statementId)) {

			throw new StatementNotFoundException(
					"Statement with statementId " + statementId + " doesn't exist. \nEnter a valid statement id.");
		}

		Statement oldStatement = statementRepo.findStatementByIdAndStatementId(id, statementId);

		if (oldStatement == null) {

			throw new StatementNotFoundException("Statement doesn't exist");
		}

		oldStatement.setBillingDate(newStatement.getBillingDate());
		oldStatement.setDueDate(newStatement.getDueDate());
		oldStatement.setDueAmount(newStatement.getDueAmount());
		oldStatement.setCardNumber(newStatement.getCardNumber());
		oldStatement.setDueAmountPaid(newStatement.getDueAmountPaid());

		statementRepo.saveAndFlush(oldStatement);

		return oldStatement;
	}

	// get statement by customer id and statement id
	public Statement getStatementByIdAndStatementId(long id, long statementId) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!statementRepo.existsById(statementId)) {

			throw new StatementNotFoundException(
					"Statement with statementId " + statementId + " doesn't exist. \nEnter a valid statement id.");
		}

		Statement statement = statementRepo.findStatementByIdAndStatementId(id, statementId);

		if (statement == null) {

			throw new StatementNotFoundException("Statement doesn't exist");
		}

		return statement;
	}

	// Get all statements for a customer
	public List<Statement> getAllStatementsById(long id) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		List<Statement> allStatements = statementRepo.findAllStatementsById(id);

		if (allStatements.isEmpty()) {
			throw new NoStatementsFoundException("No Statements Present");
		}
		return allStatements;
	}

	// get latest billing date
	public LocalDate getLatestBillingDate(long id, String cardNumber) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		return statementRepo.findLatestBillingDate(id, cardNumber);
	}

	// get Statement by card Number
	public Statement getStatementByIdAndCardNumber(long id, String cardNumber) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		LocalDate latestBillingDate = statementRepo.findLatestBillingDate(id, cardNumber);

		if (latestBillingDate == null) {

			throw new NoStatementsFoundException("No Statement exist for this card");
		}

		Statement statement = statementRepo.findStatementByIdAndCardNumber(id, cardNumber, latestBillingDate);

		if (statement == null) {

			throw new StatementNotFoundException("Statement does not exist");
		}

		return statement;
	}

	// get all statement by card number
	public List<Statement> getStatementsByIdAndCardNumber(long id, String cardNumber) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		List<Statement> statements = statementRepo.findStatementsByIdAndCardNumber(id, cardNumber);

		if (statements.isEmpty()) {

			throw new NoStatementsFoundException("No Statement Found");
		}

		return statements;

	}

}
