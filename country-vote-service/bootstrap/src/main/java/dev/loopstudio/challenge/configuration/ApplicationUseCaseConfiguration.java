package dev.loopstudio.challenge.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {
                "dev.loopstudio.challenge.application.usecase"
        },
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        }
)
public class ApplicationUseCaseConfiguration {
}
