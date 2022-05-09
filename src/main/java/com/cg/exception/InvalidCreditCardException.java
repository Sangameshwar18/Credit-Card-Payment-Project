package com.cg.exception;

public class InvalidCreditCardException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCreditCardException() {
	}

	public InvalidCreditCardException(String message) {

		super(message);
	}

}
