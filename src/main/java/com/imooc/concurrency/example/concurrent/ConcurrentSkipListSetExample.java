package com.imooc.concurrency.example.concurrent;

import com.imooc.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadSafe
class ConcurrentSkipListSetExample {

    private static Set<Integer> list = new ConcurrentSkipListSet<>();
    /** 请求总数 */
    private static int clientTotal = 5000;
    /** 同时并发执行的线程数 */
    private static int threadTotal = 200;


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
        log.info("list size:{}", list.size());
    }

    private static void update(int count) {
        list.add(count);
    }
}
