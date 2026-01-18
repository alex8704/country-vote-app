package dev.loopstudio.challende.domain.model.vote;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record Vote(
        Long id,
        Voter voter,
        Long votedCountryId,
        ZonedDateTime voteDatetime
) {
}
