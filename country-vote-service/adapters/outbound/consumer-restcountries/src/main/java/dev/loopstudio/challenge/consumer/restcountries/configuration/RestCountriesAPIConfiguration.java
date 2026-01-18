package dev.loopstudio.challenge.consumer.restcountries.configuration;

import dev.loopstudio.challenge.consumer.restcountries.client.RestCountriesAPIClient;
import dev.loopstudio.challenge.consumer.restcountries.client.RestCountriesAPIErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RestCountriesAPIProperties.class)
public class RestCountriesAPIConfiguration {
    final RestCountriesAPIProperties apiProperties;
    final RestCountriesAPIErrorHandler restCountriesAPIErrorHandler;

    @Bean
    public RestCountriesAPIClient restCountriesAPIClient() {
        return new RestCountriesAPIClient(webClient());
    }


    private WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiProperties.getBaseUrl())
                .filter(buildCommonHeadersFilter())
                .filter(restCountriesAPIErrorHandler)
                .build();
    }

    private ExchangeFilterFunction buildCommonHeadersFilter() {
        return (request, next) ->
                next.exchange(ClientRequest.from(request)
                        .headers(headers -> headers.setAccept(List.of(MediaType.APPLICATION_JSON)))
                        .build());
    }
}
