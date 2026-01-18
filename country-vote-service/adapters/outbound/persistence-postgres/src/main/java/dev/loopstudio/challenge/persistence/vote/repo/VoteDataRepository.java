package dev.loopstudio.challenge.persistence.vote.repo;

import dev.loopstudio.challenge.persistence.vote.entity.VoteData;
import dev.loopstudio.challenge.persistence.vote.projection.CountryVoteCountData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VoteDataRepository extends ReactiveCrudRepository<VoteData, Long> {
    Mono<Boolean> existsByVoterEmail(String voterEmail);

    @Query("""
            SELECT
                c.id countryId,
                c.alpha3_code countryAlpha3Code,
                c.common_name countryCommonName,
                c.official_name countryOfficialName,
                c.capital_city countryCapitalCity,
                c.region countryRegion,
                c.subregion countrySubregion,
                COUNT(v.id) voteCount
            FROM votes v
            JOIN voted_countries c ON v.voted_country_id = c.id
            GROUP BY
                c.id,
                c.alpha3_code,
                c.common_name,
                c.official_name,
                c.capital_city,
                c.region,
                c.subregion
            ORDER BY COUNT(v.id) DESC
            LIMIT :rankingSize
            """)
    Flux<CountryVoteCountData> findTopNVotedCountry(@Param("rankingSize") int rankingSize);
}
