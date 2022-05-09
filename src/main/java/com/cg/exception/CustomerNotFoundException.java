package com.cg.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
