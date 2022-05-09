package com.cg.bean;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CreditCard {

	@NotNull(message = "CardId can't be null")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private long cardId;

	@NotNull(message = "Card Name can't be null")
	@NotBlank(message = "Card Name can't be blank")
	@Column(name = "card_name")
	private String cardName;

	@NotNull(message = "Card Type can't be null")
	@NotBlank(message = "Card Type can't be blank")
	@Column(name = "card_type")
	private String cardType;

	@NotNull(message = "Card Number can't be null")
	@NotBlank(message = "Card Number can't be blank")
	@Column(name = "card_number")
	private String cardNumber;

	@NotNull(message = "Expiry Date can't be null")
	@Column(name = "date_of_expiry")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;

	@Column(name = "bank_name")
	private String bankName;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	public CreditCard() {
	}

	public CreditCard(@NotNull(message = "CardId can't be null") long cardId,
			@NotNull(message = "Card Name can't be null") @NotBlank(message = "Card Name can't be blank") String cardName,
			@NotNull(message = "Card Type can't be null") @NotBlank(message = "Card Type can't be blank") String cardType,
			@NotNull(message = "Card Number can't be null") @NotBlank(message = "Card Number can't be blank") String cardNumber,
			@NotNull(message = "Expiry Date can't be null") LocalDate expiryDate, String bankName, Customer customer) {
		super();
		this.cardId = cardId;
		this.cardName = cardName;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.customer = customer;
	}

	public CreditCard(
			@NotNull(message = "Card Name can't be null") @NotBlank(message = "Card Name can't be blank") String cardName,
			@NotNull(message = "Card Type can't be null") @NotBlank(message = "Card Type can't be blank") String cardType,
			@NotNull(message = "Card Number can't be null") @NotBlank(message = "Card Number can't be blank") String cardNumber,
			@NotNull(message = "Expiry Date can't be null") LocalDate expiryDate, String bankName, Customer customer) {
		super();
		this.cardName = cardName;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.customer = customer;
	}

	public CreditCard(
			@NotNull(message = "Card Name can't be null") @NotBlank(message = "Card Name can't be blank") String cardName,
			@NotNull(message = "Card Type can't be null") @NotBlank(message = "Card Type can't be blank") String cardType,
			@NotNull(message = "Card Number can't be null") @NotBlank(message = "Card Number can't be blank") String cardNumber,
			@NotNull(message = "Expiry Date can't be null") LocalDate expiryDate, String bankName) {
		super();
		this.cardName = cardName;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(@JsonFormat(pattern = "yyyy-MM-dd") LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CreditCard [cardId=" + cardId + ", cardName=" + cardName + ", cardType=" + cardType + ", cardNumber="
				+ cardNumber + ", expiryDate=" + expiryDate + ", bankName=" + bankName + "]";
	}

}
