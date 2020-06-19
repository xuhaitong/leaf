package com.leaf.plugin.singleton;

import java.text.MessageFormat;

/**
 * @author: Leaf Xu
 * @description: 单例模式-内部类
 * @Date: 2020-06-18 14:10
 **/
public class SingletonInnerClass {
    static {
        Thread thread = Thread.currentThread();
        System.out.println(MessageFormat.format("this is outer class static-- {0}",thread.getName()));
    }
    private SingletonInnerClass(){}
    private static class InnerClass{
        static {
            Thread thread = Thread.currentThread();
            System.out.println(MessageFormat.format("this is inner class static-- {0}",thread.getName()));
        }
        private  static final SingletonInnerClass instance = new SingletonInnerClass();
    }

    public static SingletonInnerClass getInstance(){
        return InnerClass.instance;
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is run method");
                SingletonInnerClass instance = getInstance();
                Thread thread = Thread.currentThread();
                System.out.println(MessageFormat.format("{0}---{1}",thread.getName(),instance.hashCode()));
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                SingletonEnum instance = SingletonEnum.InstanceEnum.INSTANCE.getSingletonEnum();
                Thread thread = Thread.currentThread();
                System.out.println(MessageFormat.format("{0}---{1}",thread.getName(),instance.hashCode()));
            }
        };
//        Thread th1 = new Thread(runnable);
//        Thread th2 = new Thread(runnable2);
//        Thread th3 = new Thread(runnable);
//        Thread th4 = new Thread(runnable2);
//        Thread th5 = new Thread(runnable);
        Thread th1 = new Thread(runnable2);
        Thread th2 = new Thread(runnable2);
        Thread th3 = new Thread(runnable2);
        Thread th4 = new Thread(runnable2);
        Thread th5 = new Thread(runnable2);
        th1.setName("this is thread th1");
        th2.setName("this is thread th2");
        th3.setName("this is thread th3");
        th4.setName("this is thread th4");
        th5.setName("this is thread th5");
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
    }
}
