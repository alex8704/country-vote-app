package dev.loopstudio.challenge.entrypoint.web.dto.vote;

import dev.loopstudio.challende.domain.model.vote.Voter;
import dev.loopstudio.challenge.application.usecase.vote.dto.RegisterVoteCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterVoteRequestDto(
        @Valid
        @NotNull
        VoterDto voter,
        @NotEmpty
        @Length(min = 3, max = 3)
        String countryAlpha3Code
) {


    public record VoterDto(
            @Email
            @NotEmpty
            @Length(max = 256)
            String email,
            @NotEmpty
            @Length(max = 256)
            String name
    ) {

    }


    public RegisterVoteCommand toCommand() {
        return new RegisterVoteCommand(
                new Voter(this.voter.email.toLowerCase(), this.voter.name),
                this.countryAlpha3Code.toUpperCase()
        );
    }
}
