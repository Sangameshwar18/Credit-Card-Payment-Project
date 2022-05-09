package com.cg.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Statement {

	@NotNull(message = "Statement Id can't be null")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statement_id")
	private long statementId;

	@NotNull(message = "Card Number can't be null")
	@NotNull(message = "Card Number can't be blank")
	private String cardNumber;

	@NotNull(message = "Due Amount can't be null")
	@Min(value = 0, message = "Due Amount can't be less than zero")
	@Column(name = "due_amount")
	private double dueAmount;

	@NotNull(message = "Billing date can't be zero")
	@Column(name = "billing_date")
	private LocalDate billingDate;

	@NotNull(message = "Due Date can't be null")
	@Column(name = "due_date")
	private LocalDate dueDate;

	@NotNull(message = "Amount paid can't be null")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "statement_paid_amount", joinColumns = @JoinColumn(name = "statement_id"), inverseJoinColumns = @JoinColumn(name = "payment_id"))
	private List<Payment> dueAmountPaid;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	public Statement() {
		super();
	}

	public Statement(@NotNull(message = "Statement Id can't be null") long statementId,
			@NotNull(message = "Card Number can't be null") @NotNull(message = "Card Number can't be blank") String cardNumber,
			@NotNull(message = "Due Amount can't be null") @Min(value = 0, message = "Due Amount can't be less than zero") double dueAmount,
			@NotNull(message = "Billing date can't be zero") LocalDate billingDate,
			@NotNull(message = "Due Date can't be null") LocalDate dueDate, Customer customer) {
		super();
		this.statementId = statementId;
		this.cardNumber = cardNumber;
		this.dueAmount = dueAmount;
		this.billingDate = billingDate;
		this.dueDate = dueDate;
		this.customer = customer;
	}

	public Statement(
			@NotNull(message = "Card Number can't be null") @NotNull(message = "Card Number can't be blank") String cardNumber,
			@NotNull(message = "Due Amount can't be null") @Min(value = 0, message = "Due Amount can't be less than zero") double dueAmount,
			@NotNull(message = "Billing date can't be zero") LocalDate billingDate,
			@NotNull(message = "Due Date can't be null") LocalDate dueDate, Customer customer) {
		super();
		this.cardNumber = cardNumber;
		this.dueAmount = dueAmount;
		this.billingDate = billingDate;
		this.dueDate = dueDate;
		this.customer = customer;
		this.dueAmountPaid = new ArrayList<Payment>();
	}

	public Statement(
			@NotNull(message = "Card Number can't be null") @NotNull(message = "Card Number can't be blank") String cardNumber,
			@NotNull(message = "Due Amount can't be null") @Min(value = 0, message = "Due Amount can't be less than zero") double dueAmount,
			@NotNull(message = "Billing date can't be zero") LocalDate billingDate,
			@NotNull(message = "Due Date can't be null") LocalDate dueDate) {
		super();
		this.cardNumber = cardNumber;
		this.dueAmount = dueAmount;
		this.billingDate = billingDate;
		this.dueDate = dueDate;
		this.dueAmountPaid = new ArrayList<Payment>();
	}

	public long getStatementId() {
		return statementId;
	}

	public void setStatementId(long statementId) {
		this.statementId = statementId;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Payment> getDueAmountPaid() {
		return dueAmountPaid;
	}

	public void setDueAmountPaid(List<Payment> dueAmountPaid) {
		this.dueAmountPaid = dueAmountPaid;
	}

	@Override
	public String toString() {
		return "Statement [statementId=" + statementId + ", dueAmount=" + dueAmount + ", billingDate=" + billingDate
				+ ", dueDate=" + dueDate + "]";
	}

}
