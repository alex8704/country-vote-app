package dev.loopstudio.challenge.consumer.restcountries.dto;

public record RestCountriesErrorInfo(
        String message,
        Integer status
) {
}
