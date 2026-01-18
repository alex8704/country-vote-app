package dev.loopstudio.challenge.entrypoint.web.errorhandling;

import java.time.Instant;
import java.util.Map;

public record ApiErrorResponse(
        String category,
        String message,
        Instant timestamp,
        Map<String, Object> details
) {
    public static ApiErrorResponse of(
            String category,
            String message,
            Map<String, Object> details
    ) {
        return new ApiErrorResponse(category, message, Instant.now(), details);
    }

    public static ApiErrorResponse of(
            String category,
            String message
    ) {
        return new ApiErrorResponse(category, message, Instant.now(), null);
    }
}
