package com.example.rishod.reactivestreamsinpractice.newsletter;

public class Main {
    public static void main(String[] args) {
        final NewsServicePublisher newsService = new NewsServicePublisher();

        final NewsLetterSubscriber subscriber = new NewsLetterSubscriber(5);
        newsService.subscribe(subscriber);

        subscriber.eventuallyReadDigest();

    }
}
