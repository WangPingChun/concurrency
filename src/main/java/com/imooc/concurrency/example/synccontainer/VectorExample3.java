package com.imooc.concurrency.example.synccontainer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
public class VectorExample3 {

    // java.util.ConcurrentModificationException
    private static void test1(List<Integer> list) {
        for (Integer integer : list) {
            if (integer.equals(3)) {
                list.remove(integer);
            }
        }
    }

    // java.util.ConcurrentModificationException
    private static void test2(List<Integer> list) {
        Iterator<Integer> iterable = list.iterator();
        while (iterable.hasNext()) {
            final Integer next = iterable.next();
            if (next.equals(3)) {
                list.remove(next);
            }
        }
    }

    // success
    private static void test3(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(3)) {
                list.remove(list.get(i));
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

//        test1(list);
//        test2(list);
        test3(list);
    }

}
