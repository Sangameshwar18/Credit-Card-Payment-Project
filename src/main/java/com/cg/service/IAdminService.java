package com.cg.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bean.Customer;
import com.cg.bean.Statement;
import com.cg.dao.ICustomerRepository;
import com.cg.dao.ITransactionRepository;

@Service
public class IAdminService {

	@Autowired
	ICustomerService custService;

	@Autowired
	IStatementService statementService;

	@Autowired
	ICustomerRepository custRepo;

	@Autowired
	ITransactionRepository transRepo;

	// Add Statement
	public ResponseEntity<String> addStatement(long id, String cardNumber) {

		// Get Customer
		Customer customer = custService.getCustomer(id);

		// Get latest statement's billing date
		final LocalDate latestBillingDate;

		LocalDate date = statementService.getLatestBillingDate(id, cardNumber);

		if (date == null) {
			latestBillingDate = LocalDate.now().minusMonths(1);
		} else {
			latestBillingDate = date;
		}

		Double totalDueAmount = transRepo.totalAmountByCard(cardNumber, latestBillingDate,
				LocalDate.now().plusMonths(1));

		if (totalDueAmount == null) {

			totalDueAmount = 0.0;
		}

		// Create Statement and add that statement to user's statements
		Statement newStatement = new Statement(cardNumber, totalDueAmount, LocalDate.now(),
				LocalDate.now().plusMonths(1), customer);

		// add Statement
		statementService.addStatement(id, newStatement);

		return new ResponseEntity<String>("Statement added successfully", HttpStatus.OK);
	}

}
