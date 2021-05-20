package com.alejandro.challenge.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alejandro.challenge.model.dto.CurrencyDTO;

@Component
public class CurrencyClient {

	@Value("${exchangeratesapi.api-key}")
	private String apiKey;
	
	@Value("${exchangeratesapi.base-url}")
	private String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<CurrencyDTO> getCurrency() {
		Map<String, Object> params = new HashMap<>();
		params.put("access_key", apiKey);
	   return this.restTemplate.getForEntity(url, CurrencyDTO.class, params);
	}
}
