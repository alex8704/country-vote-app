package dev.loopstudio.challenge.entrypoint.web.controllers.vote;

import dev.loopstudio.challende.domain.aggregates.vote.CountryVoteCount;
import dev.loopstudio.challende.domain.model.vote.Vote;
import dev.loopstudio.challenge.application.usecase.vote.GetTopVotedCountriesUseCase;
import dev.loopstudio.challenge.application.usecase.vote.RegisterVoteUseCase;
import dev.loopstudio.challenge.application.usecase.vote.dto.GetTopVotedCountriesCommand;
import dev.loopstudio.challenge.entrypoint.web.dto.vote.RegisterVoteRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/voting")
@RequiredArgsConstructor
public class VotingRestController {
    static final int DEFAULT_RANKING_SIZE = 10;
    final RegisterVoteUseCase registerVoteUseCase;
    final GetTopVotedCountriesUseCase getTopVotedCountriesUseCase;

    @PostMapping
    public @ResponseBody Mono<Vote> registerVote(
            @Valid @RequestBody RegisterVoteRequestDto requestDto) {
        return registerVoteUseCase.execute(requestDto.toCommand());
    }

    @GetMapping("/ranking")
    public @ResponseBody Flux<CountryVoteCount> getTopVotedCountries() {
        return getTopVotedCountriesUseCase.execute(new GetTopVotedCountriesCommand(DEFAULT_RANKING_SIZE));
    }
}
