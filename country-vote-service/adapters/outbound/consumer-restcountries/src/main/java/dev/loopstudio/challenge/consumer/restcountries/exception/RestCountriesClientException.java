package dev.loopstudio.challenge.consumer.restcountries.exception;

import lombok.Getter;

import static org.apache.commons.lang3.ObjectUtils.getIfNull;

@Getter
public class RestCountriesClientException extends RestCountriesException {
    final Integer statusCode;
    public RestCountriesClientException(String message) {
        this(message, null);
    }

    public RestCountriesClientException(String message, Integer statusCode) {
        super(message);
        this.statusCode = getIfNull(statusCode, -1);
    }
}
