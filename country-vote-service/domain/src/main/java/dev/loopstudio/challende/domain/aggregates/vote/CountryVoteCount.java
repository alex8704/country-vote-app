package dev.loopstudio.challende.domain.aggregates.vote;

import dev.loopstudio.challende.domain.model.vote.VotedCountry;

public record CountryVoteCount(
        VotedCountry votedCountry,
        Integer voteCount
) {
}
