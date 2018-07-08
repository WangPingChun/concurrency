package com.imooc.concurrency.example.count;

import com.imooc.concurrency.annotation.ThreadNotSafe;
import com.imooc.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : chris
 * 2018-07-07
 */
@Slf4j
@ThreadNotSafe
public class CountExample4 {
    /**
     * 请求总数
     */
    private static int clientTotal = 50000;
    /**
     * 同时并发执行的线程数
     */
    private static int threadTotal = 2000;

    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error -> {}", e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        // 1.count
        // 2.+1
        // 3.count
        count++;
    }
}
