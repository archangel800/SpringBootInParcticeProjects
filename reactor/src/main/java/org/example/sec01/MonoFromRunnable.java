package org.example.sec01;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("");

        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> System.out.println("Process is done. Sendind emails ..."));
    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            try {
                Util.sleepSeconds(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Operation Completed");
        };
    }
}
