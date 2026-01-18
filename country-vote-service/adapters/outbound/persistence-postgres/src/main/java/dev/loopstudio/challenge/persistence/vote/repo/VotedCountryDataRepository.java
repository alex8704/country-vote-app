package dev.loopstudio.challenge.persistence.vote.repo;

import dev.loopstudio.challenge.persistence.vote.entity.VotedCountryData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface VotedCountryDataRepository extends ReactiveCrudRepository<VotedCountryData, Long> {
    Mono<VotedCountryData> findFirstByAlpha3Code(String alpha3Code);
}
