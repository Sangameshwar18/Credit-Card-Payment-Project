package com.cg.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;

	@Column(name = "payment_method")
	private String paymentMethod;

	@NotNull(message = "Paid amount can't be null")
	@Min(value = 0, message = "Paid amount can't be negative")
	@Column(name = "Paid_amount")
	private double amountPaid;

	public Payment() {
	}

	public Payment(long paymentId, String paymentMethod, double amountPaid) {
		super();
		this.paymentId = paymentId;
		this.paymentMethod = paymentMethod;
		this.amountPaid = amountPaid;
	}

	public Payment(String paymentMethod,
			@NotNull(message = "Paid amount can't be null") @Min(value = 0, message = "Paid amount can't be negative") double amountPaid) {
		super();
		this.paymentMethod = paymentMethod;
		this.amountPaid = amountPaid;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String method) {
		this.paymentMethod = method;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentMethod=" + paymentMethod + ", amountPaid=" + amountPaid
				+ "]";
	}

}
