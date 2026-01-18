package dev.loopstudio.challenge.application.usecase.vote.exception;

import dev.loopstudio.challenge.application.exception.ApplicationException;

public class VoterAlreadyParticipatedException extends ApplicationException {
    public VoterAlreadyParticipatedException(String message) {
        super(message);
    }
}
