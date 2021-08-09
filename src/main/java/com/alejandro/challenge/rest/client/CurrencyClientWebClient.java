package com.alejandro.challenge.rest.client;

import com.alejandro.challenge.model.dto.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CurrencyClientWebClient implements CurrencyClient<Mono<CurrencyDTO>>  {

    @Autowired
    @Qualifier(value = "exchangerApi")
    private WebClient webClient;

    @Value("${router.resource}")
    private String resource;

    @Value("${router.api-key}")
    private String apiKey;

    @Value("${router.param.api-key}")
    private String paramKey;


    @Override
    public Mono<CurrencyDTO> getCurrency() {
        return webClient.get()
                .uri(uriBuilder ->
                    uriBuilder
                            .path(this.resource)
                            .queryParam(this.paramKey, this.apiKey)
                            .build()
                )
                .retrieve()
                .bodyToMono(CurrencyDTO.class);
    }
}
