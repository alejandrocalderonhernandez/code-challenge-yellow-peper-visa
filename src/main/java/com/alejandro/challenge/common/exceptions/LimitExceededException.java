package com.alejandro.challenge.common.exceptions;

import com.alejandro.challenge.common.constants.ExceptionMessages;

public class LimitExceededException extends RuntimeException {

	public LimitExceededException() {
		super(ExceptionMessages.LIMIT_EXCEEDED);
	}
	
	private static final long serialVersionUID = 87694332344321L;
}
