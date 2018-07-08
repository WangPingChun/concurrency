package com.imooc.concurrency.example.synccontainer;

import com.imooc.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadNotSafe
public class VectorExample2 {

    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < list.size(); i++) {
                    list.remove(i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i);
                }
            });

            thread1.start();
            thread2.start();
        }
    }

}
