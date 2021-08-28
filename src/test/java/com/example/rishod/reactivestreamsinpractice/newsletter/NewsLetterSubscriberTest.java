package com.example.rishod.reactivestreamsinpractice.newsletter;

import com.example.rishod.reactivestreamsinpractice.newsletter.NewsLetter;
import com.example.rishod.reactivestreamsinpractice.newsletter.NewsLetterSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.tck.SubscriberBlackboxVerification;
import org.reactivestreams.tck.TestEnvironment;

class NewsLetterSubscriberTest extends SubscriberBlackboxVerification<NewsLetter> {

    protected NewsLetterSubscriberTest() {
        super(new TestEnvironment());
    }

    @Override
    public Subscriber<NewsLetter> createSubscriber() {
        return null;
    }

    @Override
    public NewsLetter createElement(int element) {
        return null;
    }

    @Override
    public void triggerRequest(Subscriber<? super NewsLetter> subscriber) {
        ((NewsLetterSubscriber) subscriber).eventuallyReadDigest();
    }
}