package com.cg.exception;

public class TransactionDetailsNotFoundException extends RuntimeException {

	static final long serialVersionUID = 1L;

	public TransactionDetailsNotFoundException() {
	}

	public TransactionDetailsNotFoundException(String message) {
		super(message);
	}
}
