package com.cg.bean;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Transaction")
public class Transaction {
	@NotNull(message = "Enter the Transaction ID")
	@Id
	@Column(name = "transactionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;

	@NotNull(message = "Enter the Card Number")
	@NotBlank(message = "Enter the Card Number")
	@Column(name = "cardNumber")
	private String cardNumber;

	@Column(name = "transactionDate")
	private LocalDate transactionDate;

	@NotBlank(message = "Enter the Status")
	@Column(name = "status")
	private String status;

	@NotNull(message = "Payment can't be null")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@Column(name = "discription")
	private String discription;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	public Transaction() {
	}

	public Transaction(@NotNull(message = "Enter the Transaction ID") long transactionId,
			@NotNull(message = "Enter the Card Number") @NotBlank(message = "Enter the Card Number") String cardNumber,
			LocalDate transactionDate, @NotBlank(message = "Enter the Status") String status,
			@NotNull(message = "Payment can't be null") Payment payment, String discription, Customer customer) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.status = status;
		this.payment = payment;
		this.discription = discription;
		this.customer = customer;
	}

	public Transaction(
			@NotNull(message = "Enter the Card Number") @NotBlank(message = "Enter the Card Number") String cardNumber,
			LocalDate transactionDate, @NotBlank(message = "Enter the Status") String status,
			@NotNull(message = "Payment can't be null") Payment payment, String discription, Customer customer) {
		super();
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.status = status;
		this.payment = payment;
		this.discription = discription;
		this.customer = customer;
	}

	public Transaction(
			@NotNull(message = "Enter the Card Number") @NotBlank(message = "Enter the Card Number") String cardNumber,
			LocalDate transactionDate, @NotBlank(message = "Enter the Status") String status,
			@NotNull(message = "Payment can't be null") Payment payment, String discription) {
		super();
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.status = status;
		this.payment = payment;
		this.discription = discription;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", cardNumber=" + cardNumber + ", transactionDate="
				+ transactionDate + ", status=" + status + ", payment=" + payment + ", discription=" + discription
				+ "]";
	}

}
