package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotation.Recommend;
import com.imooc.concurrency.annotation.ThreadSafe;

/**
 * 枚举模式.
 *
 * @author : chris
 * 2018-07-08
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    /** 私有的构造函数 */
    private SingletonExample7() {
    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singletonExample7;

        /** jvm保证这个方法绝对只调用一次 */
        Singleton() {
            singletonExample7 = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singletonExample7;
        }
    }
}
