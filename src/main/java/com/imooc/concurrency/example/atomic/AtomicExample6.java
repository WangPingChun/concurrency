package com.imooc.concurrency.example.atomic;

import com.imooc.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author : chris
 * 2018-07-07
 */
@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean isHappend = new AtomicBoolean(false);
    /** 请求总数 */
    private static int clientTotal = 50000;
    /** 同时并发执行的线程数 */
    private static int threadTotal = 2000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error -> {}", e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappend:{}",isHappend);
    }

    private static void test() {
        if (isHappend.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}
