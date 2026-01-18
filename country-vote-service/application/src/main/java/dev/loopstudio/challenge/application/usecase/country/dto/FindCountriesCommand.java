package dev.loopstudio.challenge.application.usecase.country.dto;

public record FindCountriesCommand(
        String searchTerm,
        int limit
) {
}
