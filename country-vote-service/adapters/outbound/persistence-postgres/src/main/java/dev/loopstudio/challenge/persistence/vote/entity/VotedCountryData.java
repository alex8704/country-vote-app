package dev.loopstudio.challenge.persistence.vote.entity;


import dev.loopstudio.challende.domain.model.vote.VotedCountry;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Builder
@Table("voted_countries")
public class VotedCountryData {
    @Id
    @Column("id")
    Long id;
    @Column("alpha3_code")
    String alpha3Code;
    @Column("common_name")
    String commonName;
    @Column("official_name")
    String officialName;
    @Column("capital_city")
    String capitalCity;
    @Column("region")
    String region;
    @Column("subregion")
    String subregion;

    public VotedCountry toDomain() {
        return new VotedCountry(
                this.id,
                this.alpha3Code,
                this.commonName,
                this.officialName,
                this.capitalCity,
                this.region,
                this.subregion
        );
    }

    public static VotedCountryData from(VotedCountry domainObj) {
        return VotedCountryData.builder()
                .id(domainObj.id())
                .alpha3Code(domainObj.alpha3Code())
                .commonName(domainObj.commonName())
                .officialName(domainObj.officialName())
                .capitalCity(domainObj.capitalCity())
                .region(domainObj.region())
                .subregion(domainObj.subregion())
                .build();
    }
}
