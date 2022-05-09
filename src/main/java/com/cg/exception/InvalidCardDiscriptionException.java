package com.cg.exception;

public class InvalidCardDiscriptionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCardDiscriptionException() {
	}

	public InvalidCardDiscriptionException(String msg) {
		super(msg);
	}
}
