package com.alejandro.challenge.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alejandro.challenge.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferDTO implements Serializable {

	private BigDecimal amount;
	private String currency;
	@JsonProperty(value = "origin_account")
	private String OriginAccount;
	@JsonProperty(value = "destination_account")
	private String DestinationAccount;
	private String description;
	
	public TransferDTO() {}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getOriginAccount() {
		return OriginAccount;
	}
	
	public void setOriginAccount(String originAccount) {
		OriginAccount = originAccount;
	}
	
	public String getDestinationAccount() {
		return DestinationAccount;
	}
	
	public void setDestinationAccount(String destinationAccount) {
		DestinationAccount = destinationAccount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}

	private static final long serialVersionUID = -1870297284421707049L;
	
}
