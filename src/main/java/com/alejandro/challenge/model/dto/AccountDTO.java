package com.alejandro.challenge.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alejandro.challenge.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO implements Serializable{

	private String account;
	@JsonProperty(value = "account_balance")
	private BigDecimal balance;
	
	public AccountDTO() {
		
	}
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	
	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}


	private static final long serialVersionUID = 23245732247975431L;
	
}
