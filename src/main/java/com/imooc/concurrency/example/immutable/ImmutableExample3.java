package com.imooc.concurrency.example.immutable;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.imooc.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    private static final List<Integer> list = ImmutableList.of(1, 2, 3, 4);
    private static final ImmutableSet set = ImmutableSet.copyOf(list);
    private static final ImmutableMap<Integer, Integer> map = ImmutableMap
            .<Integer, Integer>builder()
            .put(1, 2)
            .put(3, 4)
            .build();


    public static void main(String[] args) {
        log.info("map ==> {}", map);
    }
}
