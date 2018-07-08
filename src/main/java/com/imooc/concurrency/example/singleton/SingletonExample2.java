package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式.
 * <p>
 * 单例的实例在类装载的时候创建
 * 在类构造时如果不做过多的初始化工作,推荐使用
 *
 * @author : chris
 * 2018-07-08
 */
@ThreadSafe
public class SingletonExample2 {
    /** 单例对象 */
    private static SingletonExample2 instance = new SingletonExample2();

    /** 私有的构造函数 */
    private SingletonExample2() {
    }

    /** 静态工厂 */
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
