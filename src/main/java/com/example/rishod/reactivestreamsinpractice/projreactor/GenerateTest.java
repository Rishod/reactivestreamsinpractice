package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;

@Slf4j
public class GenerateTest {

    public static void main(String[] args) throws InterruptedException {
        Flux.generate(() -> Tuples.of(0L, 1L), (state, sink) -> {
            log.info("Generated value: {}", state.getT2());
            sink.next(state.getT2());

            long newValue = state.getT1() + state.getT2();
            return Tuples.of(state.getT2(), newValue);
        }).delayElements(Duration.ofMillis(1))
                .take(7)
                .subscribe(Utils::showOnNext);

        Thread.sleep(3000L);
    }
}
