package dev.loopstudio.challenge.application.usecase.vote;

import dev.loopstudio.challende.domain.aggregates.vote.CountryVoteCount;
import dev.loopstudio.challende.domain.ports.vote.VotePort;
import dev.loopstudio.challenge.application.usecase.vote.dto.GetTopVotedCountriesCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTopVotedCountriesUseCase {
    final VotePort votePort;

    public Flux<CountryVoteCount> execute(GetTopVotedCountriesCommand command) {
        return votePort.findTopNVotedCountry(command.rankingSize());
    }
}
