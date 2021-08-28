package com.example.rishod.reactivestreamsinpractice.projreactor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    public static void showOnNext(Object o) {
        log.info("onNext: {}", o);
    }

    public static void showErrorMessage(Throwable throwable) {
        log.info("Error: {}", throwable.getMessage());
    }

    public static void showComplete() {
        log.info("onComplete");
    }
}
