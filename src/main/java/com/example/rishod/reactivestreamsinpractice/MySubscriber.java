package com.example.rishod.reactivestreamsinpractice;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class MySubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        super.hookOnSubscribe(subscription);
    }
}
