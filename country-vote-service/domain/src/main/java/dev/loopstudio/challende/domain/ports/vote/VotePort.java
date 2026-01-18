package dev.loopstudio.challende.domain.ports.vote;

import dev.loopstudio.challende.domain.aggregates.vote.CountryVoteCount;
import dev.loopstudio.challende.domain.model.vote.Vote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VotePort {
    Mono<Vote> save(Vote vote);
    Mono<Boolean> anyMatchesByVoterEmail(String voterEmail);
    Flux<CountryVoteCount> findTopNVotedCountry(int rankingSize);
}
