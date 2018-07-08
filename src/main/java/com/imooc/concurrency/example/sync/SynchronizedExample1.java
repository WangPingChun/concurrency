package com.imooc.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : chris
 * 2018-07-07
 */
@Slf4j
public class SynchronizedExample1 {

    /** 修饰一个代码块 */
    public void test1(int j) {
        // 作用于调用的对象
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    /** 修饰一个方法 */
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        final ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(() -> {
            example1.test2(1);
        });
        threadPool.execute(() -> {
            example2.test2(2);
        });

        threadPool.shutdown();
    }
}
