package com.cg.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Customer")
public class Customer extends User {

	@NotNull(message = "Customer name can't be null.\nEnter customer name")
	@NotBlank(message = "Customer name can't be blank.\nEnter customer name")
	@Column(name = "Name")
	private String name;

	@NotNull(message = "Customer email can't be null.\nEnter customer email")
	@NotBlank(message = "Customer email can't be blank.\nEnter customer email")
	@Email(message = "Email is not in well format")
	@Column(name = "Email")
	private String email;

	@NotNull(message = "Customer contact no. can't be null.\nEnter customer contact no.")
	@NotBlank(message = "Customer contact no. can't be blank.\nEnter customer contact no.")
	@Column(name = "ContactNo", length = 10)
	private String contactNo;

	@NotNull(message = "Customer Date of Birth can't be null.\nEnter Date of Birth.")
	@Column(name = "DOB")
	private LocalDate dob;

	@NotNull(message = "Customer Address can't be null.\n Enter Address.")
	@Column(name = "Address")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Address> address;

	@NotNull
	@Column(name = "CreditCard")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<CreditCard> creditCards;

	@NotNull
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_account", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "account_number"))
	private List<Account> accounts;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Transaction> transcationList;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Statement> statementList;

	public Customer() {
	}

	public Customer(
			@NotNull(message = "Customer name can't be null.\nEnter customer name") @NotBlank(message = "Customer name can't be blank.\nEnter customer name") String name,
			@NotNull(message = "Customer email can't be null.\nEnter customer email") @NotBlank(message = "Customer email can't be blank.\nEnter customer email") String email,
			@NotNull(message = "Customer contact no. can't be null.\nEnter customer contact no.") @NotBlank(message = "Customer contact no. can't be blank.\nEnter customer contact no.") String contactNo,
			@NotNull(message = "Customer Date of Birth can't be null.\nEnter Date of Birth.") LocalDate dob,
			@NotNull(message = "Customer Address can't be null.\n Enter Address.") List<Address> address,
			@NotNull List<CreditCard> creditCards, @NotNull List<Account> accounts,
			@NotNull List<Transaction> transcationList, @NotNull List<Statement> statementList) {
		super();
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
		this.creditCards = creditCards;
		this.accounts = accounts;
		this.transcationList = transcationList;
		this.statementList = statementList;
	}

	public Customer(String userId, String password, String role,
			@NotNull(message = "Customer name can't be null.\nEnter customer name") @NotBlank(message = "Customer name can't be blank.\nEnter customer name") String name,
			@NotNull(message = "Customer email can't be null.\nEnter customer email") @NotBlank(message = "Customer email can't be blank.\nEnter customer email") String email,
			@NotNull(message = "Customer contact no. can't be null.\nEnter customer contact no.") @NotBlank(message = "Customer contact no. can't be blank.\nEnter customer contact no.") String contactNo,
			@NotNull(message = "Customer Date of Birth can't be null.\nEnter Date of Birth.") LocalDate dob,
			@NotNull(message = "Customer Address can't be null.\n Enter Address.") List<Address> address) {
		super(userId, password, role);
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
		this.accounts = new ArrayList<Account>();
		this.creditCards = new ArrayList<CreditCard>();
		this.transcationList = new ArrayList<Transaction>();
		this.statementList = new ArrayList<Statement>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Transaction> getTranscationList() {
		return transcationList;
	}

	public void setTranscationList(List<Transaction> transcationList) {
		this.transcationList = transcationList;
	}

	public List<Statement> getStatementList() {
		return statementList;
	}

	public void setStatementList(List<Statement> statementList) {
		this.statementList = statementList;
	}

}
