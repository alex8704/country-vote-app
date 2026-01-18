package dev.loopstudio.challenge.consumer.restcountries;

import dev.loopstudio.challende.domain.model.country.Country;
import dev.loopstudio.challende.domain.ports.country.CountryPort;
import dev.loopstudio.challenge.application.usecase.country.exception.InvalidCountryException;
import dev.loopstudio.challenge.consumer.restcountries.client.RestCountriesAPIClient;
import dev.loopstudio.challenge.consumer.restcountries.dto.CountryData;
import dev.loopstudio.challenge.consumer.restcountries.exception.RestCountriesClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CountryHttpAdapter implements CountryPort {
    final RestCountriesAPIClient restCountriesClient;

    @Override
    public Flux<Country> findByName(String searchTerm, int limit) {
        return restCountriesClient.findByName(searchTerm, limit)
                .map(CountryData::toDomain);
    }

    @Override
    public Mono<Country> findByAlpha3Code(String alpha3Code) {
        return restCountriesClient.findByAlpha3Code(alpha3Code)
                .map(CountryData::toDomain)
                .onErrorResume(RestCountriesClientException.class,
                        ex -> Mono.error(new InvalidCountryException(String.format("El código de país [ %s ] no es válido", alpha3Code)))
                );
    }
}
