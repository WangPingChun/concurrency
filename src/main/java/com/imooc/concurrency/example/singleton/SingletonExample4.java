package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotation.Recommend;
import com.imooc.concurrency.annotation.ThreadNotSafe;

/**
 * 懒汉模式--双重同步锁单例模式.
 * <p>
 * 单例的实例在第一次使用的时候才创建
 *
 * @author : chris
 * 2018-07-08
 */
@ThreadNotSafe
@Recommend
public class SingletonExample4 {
    /** 单例对象 */
    private static SingletonExample4 instance = null;

    /** 私有的构造函数 */
    private SingletonExample4() {
    }

    /** 静态工厂 */
    public static SingletonExample4 getInstance() {
        // 双重检测机制
        if (instance == null) {
            synchronized (SingletonExample1.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
