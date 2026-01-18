package dev.loopstudio.challenge.application.usecase.vote;

import dev.loopstudio.challende.domain.model.vote.Vote;
import dev.loopstudio.challende.domain.model.vote.VotedCountry;
import dev.loopstudio.challende.domain.model.vote.Voter;
import dev.loopstudio.challende.domain.ports.country.CountryPort;
import dev.loopstudio.challende.domain.ports.vote.VotePort;
import dev.loopstudio.challende.domain.ports.vote.VotedCountryPort;
import dev.loopstudio.challenge.application.usecase.vote.dto.RegisterVoteCommand;
import dev.loopstudio.challenge.application.usecase.vote.exception.VoterAlreadyParticipatedException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

import static reactor.core.publisher.Mono.defer;

@RequiredArgsConstructor
public class RegisterVoteUseCase {
    final CountryPort countryPort;
    final VotedCountryPort votedCountryPort;
    final VotePort votePort;

    public Mono<Vote> execute(RegisterVoteCommand command) {
        return checkVoterNotParticipatedPreviously(command.voter().email())
                .then(defer(() ->
                        retrieveAlreadyVotedCountryIfExists(command.countryAlpha3Code()))
                        .switchIfEmpty(defer(() -> retrieveCountryAndSave(command.countryAlpha3Code())))
                ).flatMap(votedCountry -> buildAndRegisterVote(command.voter(), votedCountry));
    }

    private <T> Mono<T> checkVoterNotParticipatedPreviously(String voterEmail) {
        return votePort.anyMatchesByVoterEmail(voterEmail)
                .filter(Boolean::booleanValue)
                .flatMap(voterAlreadyParticipated ->
                        Mono.error(new VoterAlreadyParticipatedException("Usted ha votado previamente, solo está permitido votar una vez")));
    }

    private Mono<VotedCountry> retrieveAlreadyVotedCountryIfExists(String alpha3Code) {
        return votedCountryPort.findByAlpha3Code(alpha3Code);
    }

    private Mono<VotedCountry> retrieveCountryAndSave(String alpha3Code) {
        return countryPort.findByAlpha3Code(alpha3Code)
                .map(country -> VotedCountry.builder()
                        .alpha3Code(country.alpha3Code())
                        .commonName(country.commonName())
                        .officialName(country.officialName())
                        .capitalCity(country.capitalCity())
                        .region(country.region())
                        .subregion(country.subregion())
                        .build()
                ).flatMap(votedCountryPort::save);
    }

    private Mono<Vote> buildAndRegisterVote(Voter voter, VotedCountry votedCountry) {
        return votePort.save(Vote.builder()
                .votedCountryId(votedCountry.id())
                .voter(voter)
                .voteDatetime(ZonedDateTime.now())
                .build()
        );
    }
}
