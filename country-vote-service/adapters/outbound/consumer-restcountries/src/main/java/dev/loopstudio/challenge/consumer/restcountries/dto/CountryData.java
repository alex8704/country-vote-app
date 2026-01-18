package dev.loopstudio.challenge.consumer.restcountries.dto;

import dev.loopstudio.challende.domain.model.country.Country;

import java.util.List;

public record CountryData(
    String cca3,
    CountryNameData name,
    List<String> capital,
    String region,
    String subregion
) {
    public record CountryNameData(
            String common,
            String official
    ) {
    }

    public String mainCapital() {
        return capital.isEmpty() ? "" : capital.getFirst();
    }

    public Country toDomain() {
        return new Country(
                this.cca3,
                this.name.common,
                this.name.official,
                this.mainCapital(),
                this.region,
                this.subregion
        );
    }
}
