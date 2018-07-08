package com.imooc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
public class CountDownLatchDemo2 {
    private final static int THREAD_COUNT = 200;

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("threadNum --> {}", threadNum);
        Thread.sleep(100);
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception --> {}", e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(1, TimeUnit.SECONDS);

        log.info("finish");

        executorService.shutdown();
    }
}
