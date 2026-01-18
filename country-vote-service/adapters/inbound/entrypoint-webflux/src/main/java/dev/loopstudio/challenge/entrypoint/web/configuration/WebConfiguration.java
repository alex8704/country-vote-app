package dev.loopstudio.challenge.entrypoint.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
public class WebConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter() {
        return new CorsWebFilter((exchange) -> {
            var config = new CorsConfiguration();
            config.addAllowedOrigin("*");
            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
            return config;
        });
    }
}
