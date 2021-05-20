package com.alejandro.challenge.common.exceptions;

import com.alejandro.challenge.common.constants.ExceptionMessages;

public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException() {
		super(ExceptionMessages.INSUFFICIENT_FUNDS);
	}
	
	private static final long serialVersionUID = 1L;
}
