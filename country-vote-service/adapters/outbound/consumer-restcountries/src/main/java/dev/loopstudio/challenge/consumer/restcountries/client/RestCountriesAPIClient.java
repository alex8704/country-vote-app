package dev.loopstudio.challenge.consumer.restcountries.client;

import dev.loopstudio.challenge.consumer.restcountries.dto.CountryData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RestCountriesAPIClient {
    static final String[] API_RESPONSE_FIELDS = {
        "cca3", "name", "capital", "region", "subregion"
    };

    final WebClient webClient;

    public Flux<CountryData> findByName(String searchTerm, int limit) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/name/{name}")
                                .queryParam("fields", buildFieldsParamValue())
                                .build(searchTerm)
                )
                .retrieve()
                .bodyToFlux(CountryData.class)
                .take(limit);
    }

    public Mono<CountryData> findByAlpha3Code(String alpha3Code) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/alpha/{alpha}")
                                .queryParam("fields", buildFieldsParamValue())
                                .build(alpha3Code)
                )
                .retrieve()
                .bodyToMono(CountryData.class);
    }

    private String buildFieldsParamValue() {
        return Stream.of(API_RESPONSE_FIELDS)
                .collect(Collectors.joining(","));
    }
}
