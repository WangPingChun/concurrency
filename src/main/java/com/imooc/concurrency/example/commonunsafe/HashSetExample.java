package com.imooc.concurrency.example.commonunsafe;

import com.imooc.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadNotSafe
public class HashSetExample {

    private static Set<Integer> set = new HashSet<>();
    /** 请求总数 */
    private static int clientTotal = 5000;
    /** 同时并发执行的线程数 */
    private static int threadTotal = 2000;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error -> {}", e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list size:{}", set.size());
    }

    private static void update(int count) {
        set.add(count);
    }
}
