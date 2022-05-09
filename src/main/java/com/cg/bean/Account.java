package com.cg.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@NotNull(message = "AccountNumber should not be null")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;

	@NotNull(message = "AccountName should not be null")
	@NotBlank(message = "AccountName should not be blank")
	@Column(name = "accountName")
	private String accountName;

	@Min(value = 5000, message = "Minimum Balance should be greater than or equal to 500")
	@Column(name = "balance")
	private double balance;

	@NotNull(message = "Account Type should not be null")
	@NotBlank(message = "Account Type should not be blank")
	@Column(name = "type")
	private String type;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "accounts")
	private List<Customer> customers;

	public Account() {
	}

	public Account(@NotNull(message = "AccountNumber should not be null") long accountNumber,
			@NotNull(message = "AccountName should not be null") @NotBlank(message = "AccountName should not be blank") String accountName,
			@Min(value = 5000, message = "Minimum Balance should be greater than or equal to 500") double balance,
			@NotNull(message = "Account Type should not be null") @NotBlank(message = "Account Type should not be blank") String type,
			List<Customer> customers) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.type = type;
		this.customers = customers;
	}

	public Account(
			@NotNull(message = "AccountName should not be null") @NotBlank(message = "AccountName should not be blank") String accountName,
			@Min(value = 5000, message = "Minimum Balance should be greater than or equal to 500") double balance,
			@NotNull(message = "Account Type should not be null") @NotBlank(message = "Account Type should not be blank") String type) {
		super();
		this.accountName = accountName;
		this.balance = balance;
		this.type = type;
	}

	public Account(
			@NotNull(message = "AccountName should not be null") @NotBlank(message = "AccountName should not be blank") String accountName,
			@Min(value = 5000, message = "Minimum Balance should be greater than or equal to 500") double balance,
			@NotNull(message = "Account Type should not be null") @NotBlank(message = "Account Type should not be blank") String type,
			List<Customer> customers) {
		super();
		this.accountName = accountName;
		this.balance = balance;
		this.type = type;
		this.customers = customers;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountName=" + accountName + ", balance=" + balance
				+ ", type=" + type + "]";
	}

}
