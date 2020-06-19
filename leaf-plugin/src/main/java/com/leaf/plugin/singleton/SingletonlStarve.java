package com.leaf.plugin.singleton;


/**
 * @author: Leaf Xu
 * @description: 单例模式-饿汉模式
 * @Date: 2020-06-18 13:59
 **/
public final class SingletonlStarve {

    private static final SingletonlStarve instance = new SingletonlStarve();
    private SingletonlStarve(){
    }
    public static SingletonlStarve getInstance(){
        return instance;
    }
}
