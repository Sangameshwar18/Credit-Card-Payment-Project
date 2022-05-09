package com.cg.exception;

@SuppressWarnings("serial")
public class NoStatementsFoundException extends RuntimeException {

	public NoStatementsFoundException() {

	}

	public NoStatementsFoundException(String msg) {
		super(msg);
	}

}
