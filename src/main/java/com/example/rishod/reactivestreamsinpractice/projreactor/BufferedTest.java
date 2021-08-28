package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class BufferedTest {
    public static void main(String[] args) {
        Flux<Flux<Integer>> windowedFlux = Flux.range(101, 20)
                .windowUntil(BufferedTest::isPrimary, true);

        windowedFlux.subscribe(window -> window.collectList().subscribe(e -> log.info("window {}", e)));
    }

    private static boolean isPrimary(Integer integer) {
        return false;
    }
}
