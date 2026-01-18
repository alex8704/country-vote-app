package dev.loopstudio.challende.domain.ports.country;

import dev.loopstudio.challende.domain.model.country.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryPort {
    Flux<Country> findByName(String searchTerm, int limit);
    Mono<Country> findByAlpha3Code(String alpha3Code);
}
