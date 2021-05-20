package com.alejandro.challenge.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResponseModel extends CommonResponseModel implements Serializable {

	@JsonProperty(value = "account_balance")
	private BigDecimal balance;

	public AccountResponseModel(String status, List<String> errors, BigDecimal balance) {
		super(status, errors);
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	private static final long serialVersionUID = -5020980087336750300L;
}
