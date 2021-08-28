package com.example.rishod.reactivestreamsinpractice.newsletter;

import org.reactivestreams.Publisher;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

class NewsServicePublisherTest extends PublisherVerification<NewsLetter> {

    public NewsServicePublisherTest() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<NewsLetter> createPublisher(long elements) {
        return null;
    }

    @Override
    public Publisher<NewsLetter> createFailedPublisher() {
        return null;
    }
}