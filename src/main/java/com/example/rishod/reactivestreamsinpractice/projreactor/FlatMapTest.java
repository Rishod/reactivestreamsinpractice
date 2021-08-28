package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Slf4j
public class FlatMapTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        Flux.just("user-1", "user-2", "user-3")
                .flatMap(user -> requestBooks(user).map(book -> user + "/" + book))
                .subscribe(e -> log.info("onNext: {}", e));
    }

    public static Flux<String> requestBooks(String user) {
        return Flux.range(1, random.nextInt(3) + 1)
                .map(i -> "book-" + i)
                .delayElements(Duration.ofMillis(3));
    }
}
