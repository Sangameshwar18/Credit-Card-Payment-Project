package com.cg.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.exception.AccessDeniedException;
import com.cg.exception.AccountNotFoundException;
import com.cg.exception.CardNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.InsufficientBalanceException;
import com.cg.exception.InvalidCredentailsException;
import com.cg.exception.NoAccountsFoundException;
import com.cg.exception.NoCustomersFoundException;
import com.cg.exception.NoPaymentMadeException;
import com.cg.exception.NoStatementsFoundException;
import com.cg.exception.StatementNotFoundException;
import com.cg.exception.TransactionDetailsNotFoundException;
import com.cg.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> customerNotFoundHandler(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoCustomersFoundException.class)
	public ResponseEntity<Object> noCustomersFoundHandler(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { AccountNotFoundException.class })
	public ResponseEntity<Object> accountNotFoundHandler(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { NoAccountsFoundException.class })
	public ResponseEntity<Object> noAccountsFoundHandler(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {

		List<String> errors = e.getConstraintViolations().stream().map((error) -> error.getMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<String>("[ERROR] : " + errors, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = { InvalidCredentailsException.class })
	public ResponseEntity<String> handleInvalidCredentialsException(Exception e) {

		return new ResponseEntity<String>("[ERROR] : " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	public ResponseEntity<String> handleUserNotFoundException(Exception e) {

		return new ResponseEntity<String>("[ERROR] : " + e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = StatementNotFoundException.class)
	public ResponseEntity<Object> statementNotFoundException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoStatementsFoundException.class)
	public ResponseEntity<Object> NoStatemensFoundException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InsufficientBalanceException.class)
	public ResponseEntity<Object> handleInsufficientBalanceException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = CardNotFoundException.class)
	public ResponseEntity<Object> handleCardNotFoundException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = TransactionDetailsNotFoundException.class)
	public ResponseEntity<Object> handleTransactionDetailsNotFoundException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoPaymentMadeException.class)
	public ResponseEntity<Object> handleNoPaymentMadeException(Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
//
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<Object> handleException(Exception e) {
//		return new ResponseEntity<Object>("OOPS... Something went wrong", HttpStatus.NOT_ACCEPTABLE);
//	}

}
