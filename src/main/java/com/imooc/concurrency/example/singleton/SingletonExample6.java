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
public class SingletonExample6 {
    /** 单例对象 */
    private static SingletonExample6 instance;

    /** 私有的构造函数 */
    private SingletonExample6() {
    }

    static {
        instance = new SingletonExample6();
    }

    /** 静态工厂 */
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
