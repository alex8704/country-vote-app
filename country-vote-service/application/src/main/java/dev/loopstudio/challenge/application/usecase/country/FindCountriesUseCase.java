package dev.loopstudio.challenge.application.usecase.country;

import dev.loopstudio.challende.domain.model.country.Country;
import dev.loopstudio.challende.domain.ports.country.CountryPort;
import dev.loopstudio.challenge.application.usecase.country.dto.FindCountriesCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindCountriesUseCase {
    static final int MAX_LIMIT = 30;
    final CountryPort countryPort;

    public Flux<Country> execute(FindCountriesCommand command) {
        return countryPort.findByName(command.searchTerm(), Math.min(MAX_LIMIT, command.limit()));
    }
}
