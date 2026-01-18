package dev.loopstudio.challende.domain.model.country;

public record Country(
        String alpha3Code,
        String commonName,
        String officialName,
        String capitalCity,
        String region,
        String subregion
) {
}
