package com.leaf.plugin.singleton;

/**
 * @author: Leaf Xu
 * @description: 单例模式-枚举
 * @Date: 2020-06-18 14:29
 **/
public class  SingletonEnum {
}
 enum InstanceEnum{
    //单例模式
    INSTANCE;
    private  SingletonEnum singletonEnum;
    private InstanceEnum(){
        this.singletonEnum = new SingletonEnum();
    }

    public SingletonEnum getSingletonEnum(){
        return this.singletonEnum;
    }
}