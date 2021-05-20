package com.alejandro.challenge.resource.error;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alejandro.challenge.common.constants.NumberConstants;
import com.alejandro.challenge.common.constants.StatusTypes;
import com.alejandro.challenge.model.response.ErrorResponseModel;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalArgumentExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResponseModel handleIllegalArgumentException (IllegalArgumentException e) {
		  List<String> errors =  Arrays.asList(e.getMessage());
		  return new ErrorResponseModel(StatusTypes.ERROR, errors, new BigDecimal(NumberConstants.ZERO));
	}
}
