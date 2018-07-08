package com.imooc.concurrency.example.publish;

import com.imooc.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadNotSafe
public class UnSafePublish {
    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();
        final String[] states = unSafePublish.getStates();
        log.info("states --> {}", Arrays.toString(states));
        unSafePublish.getStates()[0] = "d";
        log.info("states --> {}", Arrays.toString(states));
    }
}
