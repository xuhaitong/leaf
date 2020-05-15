package com.leaf.cloud.config.auth.thread;

@FunctionalInterface
public interface ThreadLocalSet {
    void set(String key,Object obj);
}
