package com.alejandro.challenge.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alejandro.challenge.business.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "transfer")
public class TransferEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private BigDecimal amount;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Currency currency;
	@JsonProperty(value = "origin_account")
	@Column(nullable = false, name = "origin_account")
	private String OriginAccount;
	@JsonProperty(value = "destination_account")
	@Column(nullable = false, name = "destination_account")
	private String DestinationAccount;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private LocalDateTime date;
	
	public TransferEntity() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	private static final long serialVersionUID = 1L;

}
