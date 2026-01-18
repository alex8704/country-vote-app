package dev.loopstudio.challenge.application.usecase.vote.dto;

import dev.loopstudio.challende.domain.model.vote.Voter;

public record RegisterVoteCommand(
        Voter voter,
        String countryAlpha3Code
) {
}
