package com.alejandro.challenge.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseModel extends CommonResponseModel implements Serializable {
	
	@JsonProperty(value = "tax_collected")
	private BigDecimal taxCollected;

	public ErrorResponseModel(String status, List<String> errors, BigDecimal taxCollected) {
		super(status, errors);
		this.taxCollected = taxCollected;
	}

	
	public BigDecimal getTaxCollected() {
		return taxCollected;
	}


	public void setTaxCollected(BigDecimal taxCollected) {
		this.taxCollected = taxCollected;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private static final long serialVersionUID = -8109449543947001530L;
}
