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

import com.cg.bean.Transaction;
import com.cg.service.ITransactionService;

@RestController
public class TransactionController {
	@Autowired
	private ITransactionService transactionService;

	// Add Transaction To the customer
	@PostMapping("/addTransaction/{id}")
	public ResponseEntity<Object> addTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {

		return transactionService.addTransaction(id, transaction);
	}

	// Remove Transaction from the customer
	@DeleteMapping("/deleteTransaction/{id}/{transactionId}")
	public ResponseEntity<Object> deleteTransaction(@PathVariable("id") long id,
			@PathVariable("transactionId") long transactionId) {

		return transactionService.removeTransaction(id, transactionId);
	}

	// Update a Transaction
	@PutMapping("/updateTransaction/{id}/{transactionId}")
	public ResponseEntity<Object> updateTransaction(@PathVariable("id") long id,
			@PathVariable("transactionId") long transactionId, @RequestBody Transaction transaction) {

		return transactionService.updateTransaction(id, transactionId, transaction);
	}

	// Get Transaction for customer
	@GetMapping("/getTransaction/{id}/{transactionId}")
	public Transaction getTransactionDetails(@PathVariable("id") long id,
			@PathVariable("transactionId") long transactionId) {

		return transactionService.getTransactionDetails(id, transactionId);
	}

	// Get All Transactions for customer
	@GetMapping("/getAllTransaction/{id}")
	public List<Transaction> getAllTransactions(@PathVariable("id") long id) {

		return transactionService.getAllTransactions(id);
	}

}
