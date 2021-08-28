package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Slf4j
public class RetryTest {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Flux.just("user-1")
                .flatMap(user ->
                        recommendedBooks(user)
                        .retryBackoff(5, Duration.ofMillis(100))
                        .timeout(Duration.ofSeconds(3))
                        .onErrorResume(e -> Flux.just("The Martin")))
                .subscribe(Utils::showOnNext, Utils::showErrorMessage, Utils::showComplete);

        Thread.sleep(15000);
    }

    public static Flux<String> recommendedBooks(final String userId) {
        return Flux.defer(() -> {
            if (random.nextInt(10) < 7) {
                return Flux.<String>error(new RuntimeException())
                        .delaySequence(Duration.ofMillis(100));
            } else {
                return Flux.just("Blue Mars", "The Expense")
                        .delayElements(Duration.ofMillis(50));
            }
        }).doOnSubscribe(s -> log.info("Request for {}", userId));
    }
}
