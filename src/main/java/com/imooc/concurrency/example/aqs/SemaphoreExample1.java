package com.imooc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
public class SemaphoreExample1 {
    private final static int THREAD_COUNT = 20;

    private static void test(int threadNum) throws InterruptedException {
        log.info("threadNum --> {}", threadNum);
        Thread.sleep(1000);
    }

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(9);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 获取多个许可
                    semaphore.acquire(3);
                    test(threadNum);
                    // 释放多个许可
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    log.error("exception --> {}", e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}
