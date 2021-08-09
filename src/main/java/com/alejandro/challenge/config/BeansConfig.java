package com.alejandro.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@PropertySource(value = "classpath:router.properties")
public class BeansConfig {

	@Value("${router.base-url}")
	private String baseUrl;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean(name = "exchangerApi")
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl(baseUrl)
				.build();
	}

}
