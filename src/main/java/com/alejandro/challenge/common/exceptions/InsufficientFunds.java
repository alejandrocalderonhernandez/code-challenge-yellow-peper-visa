package com.alejandro.challenge.common.exceptions;

import com.alejandro.challenge.common.constants.ExceptionMessages;

public class InsufficientFunds extends RuntimeException {

	public InsufficientFunds() {
		super(ExceptionMessages.INSUFFICIENT_FUNDS);
	}
	
	private static final long serialVersionUID = 1L;
}
