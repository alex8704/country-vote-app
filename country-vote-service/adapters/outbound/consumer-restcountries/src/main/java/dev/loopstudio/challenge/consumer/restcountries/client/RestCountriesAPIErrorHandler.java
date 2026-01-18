package dev.loopstudio.challenge.consumer.restcountries.client;

import dev.loopstudio.challenge.consumer.restcountries.dto.RestCountriesErrorInfo;
import dev.loopstudio.challenge.consumer.restcountries.exception.RestCountriesClientException;
import dev.loopstudio.challenge.consumer.restcountries.exception.RestCountriesConnectionFailureException;
import io.netty.channel.ChannelException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.PrematureCloseException;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.stream.Stream;

import static dev.loopstudio.challenge.consumer.restcountries.util.ReactiveIOUtil.wrapBlockingCall;
import static org.apache.commons.lang3.ObjectUtils.getIfNull;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

@Component
@RequiredArgsConstructor
public class RestCountriesAPIErrorHandler implements ExchangeFilterFunction {
    final ObjectMapper objectMapper;

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return next.exchange(request)
                .flatMap(clientResponse ->
                        clientResponse.statusCode().isError() ?
                                handleErrorResponse(clientResponse) : Mono.just(clientResponse)
                ).onErrorMap(this::looksLikeNetworkFailure, this::createNetworkFailureException);
    }


    private Mono<ClientResponse> handleErrorResponse(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class)
                .flatMap(responseBodyContent -> responseBodyToJsonTree(responseBodyContent)
                        .flatMap(this::jsonTreeToErrorInfo)
                        .flatMap(errorResponse -> Mono.<ClientResponse>error(new RestCountriesClientException(errorResponse.message(), clientResponse.statusCode().value())))
                        .onErrorMap(JacksonException.class, (ex) -> new RestCountriesClientException(responseBodyContent, clientResponse.statusCode().value())));

    }

    private Mono<JsonNode> responseBodyToJsonTree(String responseBodyContent) {
        return wrapBlockingCall(() -> objectMapper.readTree(responseBodyContent));
    }

    private Mono<RestCountriesErrorInfo> jsonTreeToErrorInfo(JsonNode jsonNode) {
        return wrapBlockingCall(() -> objectMapper.treeToValue(jsonNode, RestCountriesErrorInfo.class));
    }

    private boolean looksLikeNetworkFailure(Throwable ex) {
        Throwable rootCause = getIfNull(getRootCause(ex), ex);
        return Stream.of(SocketException.class,
                        ChannelException.class,
                        PrematureCloseException.class,
                        UnknownHostException.class)
                .anyMatch(exceptionType -> exceptionType.isAssignableFrom(rootCause.getClass()));
    }

    private RestCountriesConnectionFailureException createNetworkFailureException(Throwable ex) {
        Throwable rootCause = getIfNull(getRootCause(ex), ex);
        return new RestCountriesConnectionFailureException(rootCause.getMessage());
    }
}
