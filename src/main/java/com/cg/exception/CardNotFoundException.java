package com.cg.exception;

public class CardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CardNotFoundException() {
	}

	public CardNotFoundException(String message) {

		super(message);
	}

}
