package dev.loopstudio.challende.domain.model.vote;

import lombok.Builder;

@Builder
public record VotedCountry(
        Long id,
        String alpha3Code,
        String commonName,
        String officialName,
        String capitalCity,
        String region,
        String subregion
) {
}
