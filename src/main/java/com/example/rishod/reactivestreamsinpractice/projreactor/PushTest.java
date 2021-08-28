package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;

@Slf4j
public class PushTest {

    public static void main(String[] args) throws InterruptedException {
        Flux.push(emitter -> IntStream.range(2000, 3000).forEach(emitter::next))
                .delayElements(Duration.ofMillis(1))
                .subscribe(e -> log.info("onNext: {}", e));

        Thread.sleep(4000);
    }
}
