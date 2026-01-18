package dev.loopstudio.challenge.application.usecase.country.exception;

import dev.loopstudio.challenge.application.exception.ApplicationException;

public class InvalidCountryException extends ApplicationException {
    public InvalidCountryException(String message) {
        super(message);
    }
}
