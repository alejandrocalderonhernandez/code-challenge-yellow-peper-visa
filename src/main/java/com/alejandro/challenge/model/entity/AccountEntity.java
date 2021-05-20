package com.alejandro.challenge.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name =  "account")
public class AccountEntity implements Serializable{


	@Id
	private String account;
	@JsonProperty(value = "account_balance")
	@Column(nullable =  false, name = "account_balance")
	private BigDecimal balance;
	@Column(nullable =  false)
	private Integer attempts;
	
	public AccountEntity() {}
	
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
	
	public Integer getAttempts() {
		return attempts;
	}
	
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}
	
	private static final long serialVersionUID = 1477745906775321L;
	
}
