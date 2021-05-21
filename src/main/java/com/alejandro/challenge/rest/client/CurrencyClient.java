package com.alejandro.challenge.rest.client;

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
	private String baseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<CurrencyDTO> getCurrency() {
		String uri = baseUrl + apiKey;
	   return this.restTemplate.getForEntity(uri, CurrencyDTO.class);
	}
}
