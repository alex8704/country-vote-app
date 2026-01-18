package dev.loopstudio.challenge.consumer.restcountries.util;

import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

import static reactor.core.scheduler.Schedulers.boundedElastic;

@UtilityClass
public class ReactiveIOUtil {
    public static <T> Mono<T> wrapBlockingCall(Callable<T> blockingCall) {
        return Mono.fromCallable(blockingCall)
                .subscribeOn(boundedElastic());
    }
}
