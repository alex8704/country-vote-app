package dev.loopstudio.challenge.application.usecase.vote.dto;

public record GetTopVotedCountriesCommand(
        int rankingSize
) {
}
