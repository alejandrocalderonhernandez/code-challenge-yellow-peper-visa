package com.alejandro.challenge.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.alejandro.challenge.util.JsonUtil;

public class CurrencyDTO implements Serializable {

	private String base;
	private Map<String, BigDecimal> rates;
	
	public CurrencyDTO() {}
	
	public String getBase() {
		return base;
	}


	public void setBase(String base) {
		this.base = base;
	}


	public Map<String, BigDecimal> getRates() {
		return rates;
	}


	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}


	private static final long serialVersionUID = 8991966603200090201L;

}
