package com.imooc.concurrency.example.immutable;


import com.google.common.collect.Maps;
import com.imooc.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadNotSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        // map = Maps.newHashMap();
        map.put(1, 3);
        log.info("map ==> {}", map);
    }

    private void test(final int a) {

    }
}
