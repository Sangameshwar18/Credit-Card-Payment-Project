package com.cg.exception;

public class InvalidCredentailsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidCredentailsException() {
	}

	public InvalidCredentailsException(String errorMsg) {

		super(errorMsg);
	}

}
