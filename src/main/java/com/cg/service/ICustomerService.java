package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bean.Account;
import com.cg.bean.Customer;
import com.cg.bean.Payment;
import com.cg.bean.Statement;
import com.cg.dao.IAccountRepository;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.NoCustomersFoundException;
import com.cg.exception.NoPaymentMadeException;

@Service
public class ICustomerService {

	@Autowired
	private ICustomerRepository crepo;

	@Autowired
	private IStatementService statementService;

	@Autowired
	private IAccountRepository accountRepo;

	public ResponseEntity<String> addCustomer(Customer newCustomer) {

		crepo.saveAndFlush(newCustomer);

		return new ResponseEntity<String>("Customer added successfully", HttpStatus.ACCEPTED);
	}

	public String removeCustomer(long custId) {

		if (!crepo.existsById(custId)) {
			throw new CustomerNotFoundException("Customer does not exist!");
		}

		crepo.deleteById(custId);
		return "Customer Record deleted";
	}

	public String updateCustomer(long custId, Customer customer) {

		if (!crepo.existsById(custId)) {
			throw new CustomerNotFoundException("Customer does not exist!");
		}

		Customer c = crepo.findById(custId).get();
		c.setName(customer.getName());
		c.setEmail(customer.getEmail());
		c.setContactNo(customer.getContactNo());
		c.setDob(customer.getDob());
		c.setAddress(customer.getAddress());
		crepo.save(c);
		return "Record Updated Successfully";
	}

	public Customer getCustomer(long custId) {

		if (!crepo.existsById(custId)) {
			throw new CustomerNotFoundException("Customer does not exist!");
		}

		return crepo.findById(custId).get();
	};

	public List<Customer> getAllCustomers() {

		if (crepo.findAll().isEmpty()) {
			throw new NoCustomersFoundException("No Customers exist!");
		}
		return crepo.findAll();
	}

	// Payment History
	public List<Payment> getPaymentHistory(long custId, long statementId) {

		if (!crepo.existsById(custId)) {

			throw new CustomerNotFoundException("Customer with id " + custId + " doesn't exist.");
		}

		List<Payment> payments = statementService.getStatementByIdAndStatementId(custId, statementId)
				.getDueAmountPaid();

		if (payments.isEmpty()) {

			throw new NoPaymentMadeException("No Payments made yet");
		}

		return payments;
	}

	// Statement History
	public List<Statement> getStatementHistory(long custId) {

		return statementService.getAllStatementsById(custId);

	}

	// Get Customer by userId
	public Customer getCustomerByUserId(String userId) {

		return crepo.findCustomerByUserId(userId);
	}

	// Pay Due Amount
	public ResponseEntity<String> payDueAmount(long id, long statementId, long accountNumber, Payment newPayment) {

		// Get Statement
		Statement statement = statementService.getStatementByIdAndStatementId(id, statementId);

		// Get Account for customer
		Account account = accountRepo.findById(accountNumber).get();

		// Check If user has balance to pay the due amount
		if (account.getBalance() < newPayment.getAmountPaid()) {
			throw new InsufficientBalanceException("Insufficient Balanace.");
		}

		// Deduct the amount from account balance
		account.setBalance(account.getBalance() - newPayment.getAmountPaid());

		// Update the account
//		accountService.updateAccount(id, account);
		accountRepo.saveAndFlush(account);

		// Add new Payment
		statement.getDueAmountPaid().add(newPayment);

		// Update Statement
		statementService.UpdateStatement(id, statementId, statement);

		return new ResponseEntity<String>("Due Amount Paid Successfully", HttpStatus.OK);

	}

}
