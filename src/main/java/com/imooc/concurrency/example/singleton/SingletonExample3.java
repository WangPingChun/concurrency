package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotation.NotRecommend;
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
@NotRecommend
public class SingletonExample3 {
    /** 单例对象 */
    private static SingletonExample3 instance = null;

    /** 私有的构造函数 */
    private SingletonExample3() {
    }

    /** 静态工厂 */
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
