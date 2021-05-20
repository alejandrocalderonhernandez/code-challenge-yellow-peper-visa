package com.alejandro.challenge.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferResponseModel  extends CommonResponseModel implements Serializable {

	@JsonProperty(value = "tax_collected")
	private BigDecimal taxCollected;
	@JsonProperty(value = "CAD")
	private BigDecimal cad;
	
	public TransferResponseModel(String status, List<String> errors, BigDecimal taxCollected, BigDecimal cad) {
		super(status, errors);
		this.taxCollected = taxCollected;
		this.cad = cad;
	}
	
	private static final long serialVersionUID = 77894321451L;
}
