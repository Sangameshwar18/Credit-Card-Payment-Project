package com.cg.exception;

public class NoCustomersFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoCustomersFoundException() {
		// TODO Auto-generated constructor stub
	}

	public NoCustomersFoundException(String msg) {
		super(msg);
	}
}
