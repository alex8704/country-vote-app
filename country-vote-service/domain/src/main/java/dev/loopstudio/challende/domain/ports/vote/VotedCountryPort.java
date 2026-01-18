package dev.loopstudio.challende.domain.ports.vote;

import dev.loopstudio.challende.domain.model.vote.VotedCountry;
import reactor.core.publisher.Mono;

public interface VotedCountryPort {
    Mono<VotedCountry> save(VotedCountry votedCountry);
    Mono<VotedCountry> findByAlpha3Code(String alpha3Code);
}
