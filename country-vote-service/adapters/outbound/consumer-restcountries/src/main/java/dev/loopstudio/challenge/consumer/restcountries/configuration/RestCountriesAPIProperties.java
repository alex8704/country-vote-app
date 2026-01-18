package dev.loopstudio.challenge.consumer.restcountries.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "consumer.rest-countries")
public class RestCountriesAPIProperties {
    String baseUrl;
}
