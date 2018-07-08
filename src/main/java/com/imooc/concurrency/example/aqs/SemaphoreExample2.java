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
public class SemaphoreExample2 {
    private final static int THREAD_COUNT = 20;

    private static void test(int threadNum) throws InterruptedException {
        log.info("threadNum --> {}", threadNum);
        Thread.sleep(1000);
    }

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 获取一个许可
                    semaphore.acquire();
                    test(threadNum);
                    // 释放一个许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception --> {}", e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }
}
