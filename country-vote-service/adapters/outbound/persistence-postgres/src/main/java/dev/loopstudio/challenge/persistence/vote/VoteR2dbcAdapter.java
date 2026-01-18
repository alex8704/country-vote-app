package dev.loopstudio.challenge.persistence.vote;

import dev.loopstudio.challende.domain.aggregates.vote.CountryVoteCount;
import dev.loopstudio.challende.domain.model.vote.Vote;
import dev.loopstudio.challende.domain.ports.vote.VotePort;
import dev.loopstudio.challenge.persistence.vote.entity.VoteData;
import dev.loopstudio.challenge.persistence.vote.projection.CountryVoteCountData;
import dev.loopstudio.challenge.persistence.vote.repo.VoteDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static dev.loopstudio.challenge.persistence.vote.entity.VoteData.from;

@Component
@RequiredArgsConstructor
public class VoteR2dbcAdapter implements VotePort {
    final VoteDataRepository voteDataRepository;

    @Override
    public Mono<Vote> save(Vote vote) {
        return voteDataRepository.save(from(vote))
                .map(VoteData::toDomain);
    }

    @Override
    public Mono<Boolean> anyMatchesByVoterEmail(String voterEmail) {
        return voteDataRepository.existsByVoterEmail(voterEmail);
    }

    @Override
    public Flux<CountryVoteCount> findTopNVotedCountry(int rankingSize) {
        return voteDataRepository.findTopNVotedCountry(rankingSize)
                .map(CountryVoteCountData::toDomain);
    }
}
