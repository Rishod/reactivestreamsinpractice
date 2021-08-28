package com.example.rishod.reactivestreamsinpractice.projreactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ContextTest {
    public static void main(String[] args) {
        Flux.range(0, 10)
                .flatMap(k -> Mono.subscriberContext().doOnNext(context -> {
                    Map<Object, Object> map = context.get("randoms");
                    map.put(k, new Random().nextGaussian());
                }).thenReturn(k))
                .publishOn(Schedulers.parallel())
                .flatMap(k -> Mono.subscriberContext().map(context -> {
                    Map<Object, Object> map = context.get("randoms");
                    return map.get(k);
                }))
                .subscriberContext(context -> context.put("randoms", new HashMap<>()))
                .blockLast();
    }
}
