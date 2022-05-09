package com.cg.exception;

@SuppressWarnings("serial")
public class StatementNotFoundException extends RuntimeException {

	public StatementNotFoundException() {

	}

	public StatementNotFoundException(String msg) {
		super(msg);
	}

}
