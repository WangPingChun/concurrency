package com.imooc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    private static void race(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {

        }
        log.info("{} continue", threadNum);
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception --> {}", e.getMessage());
                }
            });
        }

        executor.shutdown();
    }
}
