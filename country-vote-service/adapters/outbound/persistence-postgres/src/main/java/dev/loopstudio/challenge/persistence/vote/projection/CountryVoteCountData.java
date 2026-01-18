package dev.loopstudio.challenge.persistence.vote.projection;

import dev.loopstudio.challende.domain.aggregates.vote.CountryVoteCount;
import dev.loopstudio.challende.domain.model.vote.VotedCountry;
import org.springframework.data.relational.core.mapping.Column;


public record CountryVoteCountData(
        @Column("countryid")
        Long countryId,
        @Column("countryalpha3code")
        String countryAlpha3Code,
        @Column("countrycommonname")
        String countryCommonName,
        @Column("countryofficialname")
        String countryOfficialName,
        @Column("countrycapitalcity")
        String countryCapitalCity,
        @Column("countryregion")
        String countryRegion,
        @Column("countrysubregion")
        String countrySubregion,
        @Column("votecount")
        Integer voteCount
) {
    public CountryVoteCount toDomain() {
        return new CountryVoteCount(
                new VotedCountry(
                        this.countryId,
                        this.countryAlpha3Code,
                        this.countryCommonName,
                        this.countryOfficialName,
                        this.countryCapitalCity,
                        this.countryRegion,
                        this.countrySubregion
                ),
                this.voteCount
        );
    }
}
