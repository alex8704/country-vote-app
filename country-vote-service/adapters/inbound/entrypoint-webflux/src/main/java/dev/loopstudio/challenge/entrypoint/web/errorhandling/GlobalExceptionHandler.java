package dev.loopstudio.challenge.entrypoint.web.errorhandling;

import dev.loopstudio.challenge.application.exception.ApplicationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    static final String DEFAULT_MESSAGE = "Hemos encontrado un error inesperado. Por favor espera un poco e intenta de nuevo.";

    @ExceptionHandler(ApplicationException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleApplication(ApplicationException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body(ApiErrorResponse.of(
                        "BUSINESS_RULE_VIOLATED",
                        ex.getMessage()
                )));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleWebExchangeBind(
            WebExchangeBindException ex
    ) {

        Map<String, Object> errors = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1 // keep first
                ));

        return Mono.just(
                ResponseEntity
                        .badRequest()
                        .body(ApiErrorResponse.of(
                                "VALIDATION_ERROR",
                                "Request validation failed",
                                errors
                        ))
        );
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleConstraintViolation(
            ConstraintViolationException ex
    ) {

        Map<String, Object> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (msg1, msg2) -> msg1
                ));

        return Mono.just(
                ResponseEntity
                        .badRequest()
                        .body(ApiErrorResponse.of(
                                "CONSTRAINT_VIOLATION",
                                "Constraint validation failed",
                                errors
                        ))
        );
    }


    @ExceptionHandler(ErrorResponseException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleErrorResponse(ErrorResponseException ex) {
        return Mono.just(ResponseEntity.status(ex.getStatusCode())
                .body(ApiErrorResponse.of(
                        "GENERAL",
                        ex.getMessage()
                )));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleResponseStatus(ResponseStatusException ex) {
        return Mono.just(ResponseEntity.status(ex.getStatusCode())
                .body(ApiErrorResponse.of(
                        "GENERAL",
                        ex.getMessage()
                )));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleException(Exception ex) {
        log.error("Unexpected technical error", ex);
        return Mono.just(ResponseEntity.internalServerError()
                .body(ApiErrorResponse.of(
                        "INTERNAL_SERVER_ERROR",
                        DEFAULT_MESSAGE,
                        Map.of("error", ex.getMessage())
                )));
    }
}
