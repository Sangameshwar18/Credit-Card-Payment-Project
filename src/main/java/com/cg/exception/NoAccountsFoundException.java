package com.cg.exception;

public class NoAccountsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoAccountsFoundException() {
	}

	public NoAccountsFoundException(String message) {
		super(message);
	}
}
