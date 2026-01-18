package dev.loopstudio.challenge.entrypoint.web.dto.country;

import dev.loopstudio.challende.domain.model.country.Country;

public record CountryDto(
        String alpha3Code,
        String commonName,
        String officialName,
        String capitalCity,
        String region,
        String subregion
) {
    public static CountryDto from(Country domainObj) {
        return new CountryDto(
                domainObj.alpha3Code(),
                domainObj.commonName(),
                domainObj.officialName(),
                domainObj.capitalCity(),
                domainObj.region(),
                domainObj.subregion()
        );
    }
}
