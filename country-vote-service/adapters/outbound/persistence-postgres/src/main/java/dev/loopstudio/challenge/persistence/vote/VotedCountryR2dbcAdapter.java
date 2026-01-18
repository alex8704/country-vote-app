package dev.loopstudio.challenge.persistence.vote;

import dev.loopstudio.challende.domain.model.vote.VotedCountry;
import dev.loopstudio.challende.domain.ports.vote.VotedCountryPort;
import dev.loopstudio.challenge.persistence.vote.entity.VotedCountryData;
import dev.loopstudio.challenge.persistence.vote.repo.VotedCountryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static dev.loopstudio.challenge.persistence.vote.entity.VotedCountryData.from;


@Component
@RequiredArgsConstructor
public class VotedCountryR2dbcAdapter implements VotedCountryPort {
    final VotedCountryDataRepository votedCountryDataRepository;

    @Override
    public Mono<VotedCountry> save(VotedCountry votedCountry) {
        return votedCountryDataRepository.save(from(votedCountry))
                .map(VotedCountryData::toDomain);
    }

    @Override
    public Mono<VotedCountry> findByAlpha3Code(String alpha3Code) {
        return votedCountryDataRepository.findFirstByAlpha3Code(alpha3Code)
                .map(VotedCountryData::toDomain);
    }

}
