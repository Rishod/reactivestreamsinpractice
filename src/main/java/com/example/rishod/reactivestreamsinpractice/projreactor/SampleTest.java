package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class SampleTest {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(1))
                .sample(Duration.ofMillis(20))
                .subscribe(e -> log.info("onNext: {}", e));

        Thread.sleep(1000);
    }
}
