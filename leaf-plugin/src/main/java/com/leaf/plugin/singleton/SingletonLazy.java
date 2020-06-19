package com.leaf.plugin.singleton;

import static com.leaf.plugin.singleton.InstanceEnum.INSTANCE;

/**
 * @author: Leaf Xu
 * @description: 单例模式-懒汉模式  懒汉双重加锁
 * @Date: 2020-06-18 14:03
 **/
public final class SingletonLazy {
    private static SingletonLazy instance = null;

    private  volatile static SingletonLazy instanceLock = null;

    private SingletonLazy(){}
    //普通懒汉模式
    public static SingletonLazy getInstance(){
        if(instance == null){
            instance = new SingletonLazy();
        }
        return instance;
    }
    //双重加锁
    public static SingletonLazy getInstanceWithLock(){
        if(instanceLock == null){
            synchronized (SingletonLazy.class){
                if(instanceLock==null){
                    instanceLock = new SingletonLazy();
                }
            }
        }
        return instanceLock;
    }

    public static void main(String[] args) {
        INSTANCE.getSingletonEnum();
    }
}
