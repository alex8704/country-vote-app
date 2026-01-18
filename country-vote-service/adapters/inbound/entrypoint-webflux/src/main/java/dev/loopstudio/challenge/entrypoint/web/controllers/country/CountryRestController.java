package dev.loopstudio.challenge.entrypoint.web.controllers.country;

import dev.loopstudio.challenge.application.usecase.country.FindCountriesUseCase;
import dev.loopstudio.challenge.application.usecase.country.dto.FindCountriesCommand;
import dev.loopstudio.challenge.entrypoint.web.dto.country.CountryDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryRestController {
    static final int SEARCH_LIMIT = 20;
    final FindCountriesUseCase findCountriesUseCase;

    @GetMapping
    public @ResponseBody Flux<CountryDto> findCountries(
            @Valid @NotEmpty @RequestParam("searchTerm") String searchTerm) {
        return findCountriesUseCase.execute(new FindCountriesCommand(searchTerm, SEARCH_LIMIT))
                .map(CountryDto::from);
    }
}
