package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式.
 * <p>
 * 单例的实例在第一次使用的时候才创建
 *
 * @author : chris
 * 2018-07-08
 */
@ThreadSafe
public class SingletonExample1 {
    /** 单例对象 */
    private static SingletonExample1 instance = null;

    /** 私有的构造函数 */
    private SingletonExample1() {
    }

    /** 静态工厂 */
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
