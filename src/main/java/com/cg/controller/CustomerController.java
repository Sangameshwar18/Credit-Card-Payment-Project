package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Payment;
import com.cg.bean.Statement;
import com.cg.bean.Transaction;
import com.cg.service.ICustomerService;
import com.cg.service.IStatementService;
import com.cg.service.ITransactionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService custService;

	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IStatementService statementService;

	// Make Transaction
	@PatchMapping("/makeTransaction/{id}")
	public ResponseEntity<Object> makeTransaction(@PathVariable("id") long id,
			@RequestBody Transaction newTransaction) {

		return transactionService.addTransaction(id, newTransaction);
	}

	// Get Due Amount and Due Date for a credit card
	@GetMapping("/getDueAmount/{id}/{cardNumber}")
	public Statement getDueAmount(@PathVariable("id") long id, @PathVariable("cardNumber") String cardNumber) {

		return statementService.getStatementByIdAndCardNumber(id, cardNumber);
	}

	// Pay Due Amount
	@PatchMapping("/payDueAmount/{id}/{statementId}/{accountNumber}")
	public ResponseEntity<String> payDueAmount(@PathVariable("id") long id,
			@PathVariable("statementId") long statementId, @PathVariable("accountNumber") long accountNumber,
			@RequestBody Payment newPayment) {

		return custService.payDueAmount(id, statementId, accountNumber, newPayment);

	}

	// Get a statement for a customer by statement Id
	@GetMapping("/getStatement/{id}/{statementId}")
	public Statement getStatementByIdAndStatementId(@PathVariable("id") long id,
			@PathVariable("statementId") long statementId) {

		return statementService.getStatementByIdAndStatementId(id, statementId);
	}

	// Get latest Statement for a customer by card number
	@GetMapping("/getStatementCC/{id}/{cardNumber}")
	public Statement getStatementByIdAndCardNumber(@PathVariable("id") long id,
			@PathVariable("cardNumber") String cardNumber) {

		return statementService.getStatementByIdAndCardNumber(id, cardNumber);
	}

	// Get all statements for a customer by card number
	@GetMapping("/getStatements/{id}/{cardNumber}")
	public List<Statement> getAllStatementsByIdAndCardNumber(@PathVariable("id") long id,
			@PathVariable("cardNumber") String cardNumber) {

		return statementService.getStatementsByIdAndCardNumber(id, cardNumber);
	}

	// Statement history
	@GetMapping("/getStatementHist/{id}")
	public List<Statement> getStatementHistory(@PathVariable("id") long id) {

		return custService.getStatementHistory(id);
	}

	// Payment History for statement
	@GetMapping("/getPaymentHistory/{id}/{statementId}")
	public List<Payment> getPaymentHistory(@PathVariable("id") long id, @PathVariable("statementId") long statementId) {

		return custService.getPaymentHistory(id, statementId);
	}

}
