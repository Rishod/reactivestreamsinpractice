package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Slf4j
public class UsingWhenTest {
    public static void main(String[] args) throws InterruptedException {
        Flux.usingWhen(
                Transaction.beginTransaction(),
                transaction -> transaction.insertRows(Flux.just("A", "B", "C")),
                Transaction::commit,
                Transaction::rollback
        ).subscribe(
                e -> log.info("onNext: {}", e),
                e -> log.info("onError: {}", e.getMessage()),
                () -> log.info("onComplete")
        );

        Thread.sleep(10000);
    }

    public static class Transaction {
        private static final Random random = new Random();
        private final int id;

        public Transaction(int id) {
            this.id = id;
            log.info("[T: {}] created", id);
        }

        public static Mono<Transaction> beginTransaction() {
            return Mono.defer(() -> Mono.just(new Transaction(random.nextInt(1000))));
        }

        public Flux<String> insertRows(Publisher<String> rows) {
            return Flux.from(rows)
                    .delayElements(Duration.ofMillis(100))
                    .flatMap(r -> random.nextInt(10) < 2 ? Mono.error(new RuntimeException("Error: " + r))
                            : Mono.just(r));
        }

        public Mono<Void> commit() {
            return Mono.defer(() -> {
                log.info("[T: {}] commit", id);
                return random.nextBoolean() ? Mono.empty() : Mono.error(new RuntimeException("Conflict"));
            });
        }

        public Mono<Void> rollback() {
            return Mono.defer(() -> {
                log.info("[T: {}] rollback", id);
                return random.nextBoolean() ? Mono.empty() : Mono.error(new RuntimeException("Connection error"));
            });
        }
    }
}
