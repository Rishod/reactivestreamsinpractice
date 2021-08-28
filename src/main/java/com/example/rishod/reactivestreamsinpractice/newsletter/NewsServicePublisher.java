package com.example.rishod.reactivestreamsinpractice.newsletter;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class NewsServicePublisher implements Publisher<NewsLetter> {
    @Override
    public void subscribe(Subscriber<? super NewsLetter> s) {

    }
}
