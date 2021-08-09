package com.alejandro.challenge.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alejandro.challenge.model.dto.CurrencyDTO;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Component
public class CurrencyClientRestTemplate implements CurrencyClient<ResponseEntity<CurrencyDTO>> {
	
	@Value("${router.base-url}")
	private String baseUrl;

	@Value("${router.resource}")
	private String resource;

	@Value("${router.api-key}")
	private String apiKey;

	@Value("${router.param.api-key}")
	private String paramKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<CurrencyDTO> getCurrency() {
		String uri = this.baseUrl + this.resource;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);
		builder.queryParam(this.paramKey, this.apiKey);
	   return this.restTemplate.getForEntity(builder.build().toUri(), CurrencyDTO.class);
	}
}
