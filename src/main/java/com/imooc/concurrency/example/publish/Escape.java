package com.imooc.concurrency.example.publish;

import com.imooc.concurrency.annotation.NotRecommend;
import com.imooc.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : chris
 * 2018-07-08
 */
@Slf4j
@ThreadNotSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }

    }

    public static void main(String[] args) {
        new Escape();
    }
}
