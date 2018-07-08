package com.imooc.concurrency.example.threadlocal;

/**
 * @author : chris
 * 2018-07-08
 */
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void clear() {
        requestHolder.remove();
    }
}
