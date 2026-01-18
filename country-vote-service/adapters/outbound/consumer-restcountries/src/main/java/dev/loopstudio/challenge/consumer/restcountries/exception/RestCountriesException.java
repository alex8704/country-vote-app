package dev.loopstudio.challenge.consumer.restcountries.exception;

public abstract class RestCountriesException extends RuntimeException {
    public RestCountriesException(String message) {
        super(message);
    }
}
