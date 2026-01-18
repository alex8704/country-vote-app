package dev.loopstudio.challenge.persistence.vote.entity;

import dev.loopstudio.challende.domain.model.vote.Vote;
import dev.loopstudio.challende.domain.model.vote.Voter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
@Table("votes")
public class VoteData {
    @Id
    @Column("id")
    Long id;
    @Column("voter_email")
    String voterEmail;
    @Column("voter_name")
    String voterName;
    @Column("voted_country_id")
    Long votedCountryId;
    @Column("vote_datetime")
    ZonedDateTime voteDatetime;


    public Vote toDomain() {
        return new Vote(
                this.id,
                new Voter(this.voterEmail, this.voterName),
                this.votedCountryId,
                this.voteDatetime
        );
    }

    public static VoteData from(Vote domainObj) {
        return VoteData.builder()
                .id(domainObj.id())
                .voterEmail(domainObj.voter().email())
                .voterName(domainObj.voter().name())
                .votedCountryId(domainObj.votedCountryId())
                .voteDatetime(domainObj.voteDatetime())
                .build();
    }
}
