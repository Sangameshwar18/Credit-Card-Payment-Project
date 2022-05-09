package com.cg.exception;

public class NoPaymentMadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoPaymentMadeException() {
	}

	public NoPaymentMadeException(String message) {

		super(message);
	}

}
