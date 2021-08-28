package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Function;

@Slf4j
public class TransformTest {
    public static void main(String[] args) {
        final Function<Flux<String>, Flux<String>> logUserInfo = stream -> stream.index()
                .doOnNext(tp -> log.info("[{}] User: {}", tp.getT1(), tp.getT2()))
                .map(Tuple2::getT2);

        Flux.range(1000, 3)
                .map(i -> "user-" + i)
                .transform(logUserInfo)
                .subscribe(Utils::showOnNext);
    }
}
