package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Account;
import com.cg.bean.Customer;
import com.cg.dao.IAccountRepository;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.AccountNotFoundException;
import com.cg.exception.CustomerNotFoundException;

@Service
public class IAccountService {

	@Autowired
	private IAccountRepository accRepo;

	@Autowired
	private ICustomerRepository custRepo;

	@Autowired
	private ICustomerService custService;

	// Add account to customer
	public String addAccount(long id, Account account) {

		Customer customer = custService.getCustomer(id);

		customer.getAccounts().add(account);

		custRepo.saveAndFlush(customer);

		return "Account added successfully";
	}

	public String updateAccount(long id, Account account) {

		if (!accRepo.existsById(id)) {
			throw new AccountNotFoundException("Account Not Found");
		}

		Account a = accRepo.findById(id).get();

		a.setAccountName(account.getAccountName());
		a.setBalance(account.getBalance());
		a.setType(account.getType());

		accRepo.saveAndFlush(a);

		return "Updated Successfully";
	}

	// Get Account for customer
	public Account getAccount(long id, long accountNumber) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!accRepo.existsById(accountNumber)) {
			throw new AccountNotFoundException("Account not found");
		}

		List<Account> accounts = accRepo.findAllAccountsById(id);

		accounts = accounts.stream().filter((acc) -> acc.getAccountNumber() == accountNumber)
				.collect(Collectors.toList());

		if (accounts.isEmpty()) {

			throw new AccountNotFoundException("Account not found");
		}

		return accounts.get(0);
	}

	public List<Account> getAllAccounts(long id) {

		if (accRepo.findAllAccountsById(id).isEmpty()) {
			throw new com.cg.exception.NoAccountsFoundException("No accounts exists!");
		}

		return accRepo.findAllAccountsById(id);
	}

	public String removeAccount(long id, long accountNumber) {

		if (!custRepo.existsById(id)) {

			throw new CustomerNotFoundException(
					"Customer with id " + id + " doesn't exist. \nEnter a valid customer id.");
		}

		if (!accRepo.existsById(accountNumber)) {
			throw new AccountNotFoundException("Account not found");
		}

		List<Account> accounts = accRepo.findAllAccountsById(id);

		accounts = accounts.stream().filter((acc) -> acc.getAccountNumber() == accountNumber)
				.collect(Collectors.toList());

		if (accounts.isEmpty()) {

			throw new AccountNotFoundException("Account not found");
		}

		Customer customer = custService.getCustomer(id);

		customer.getAccounts().remove(accounts.get(0));

		custRepo.saveAndFlush(customer);

		accRepo.deleteById(accountNumber);

		return ("Account deleted successfully");
	}

}
