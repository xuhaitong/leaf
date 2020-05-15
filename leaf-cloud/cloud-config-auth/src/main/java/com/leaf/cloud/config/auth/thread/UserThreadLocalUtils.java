package com.leaf.cloud.config.auth.thread;

import java.util.HashMap;
import java.util.Map;

public class UserThreadLocalUtils {

    private static final ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    private UserThreadLocalUtils() {

    }

    public static void setToken(String token){
       //localSet.set(UserLocal.KEY_TOKEN,token);
       set(UserLocal.KEY_TOKEN,token);
    }

    public static void setUser(String user){
        set(UserLocal.KEY_USER,user);
        Map res = getAll();
        System.out.println(res);
    }

    public static void set(String key,Object obj){
        Map<String,Object> local = threadLocal.get();
        if(local == null){
            local = new HashMap<>();
        }
        local.put(key,obj);
        threadLocal.set(local);
    }

    public static String getToken(){
        //localSet.set(UserLocal.KEY_TOKEN,token);
        return (String)get(UserLocal.KEY_TOKEN);
    }

    public static Object getUser(){
        return get(UserLocal.KEY_USER);
    }

    public static Object get(String key){
        Map<String,Object> local = threadLocal.get();
        if(local == null){
            local = new HashMap<>();
        }
        return local.get(key);
    }

    public static Map getAll(){
        Map<String,Object> local = threadLocal.get();
        if(local == null){
            local = new HashMap<>();
        }
        return local;
    }


    private static final ThreadLocalSet localSet =
            (keyp,objp) -> {
                Map<String,Object> local = threadLocal.get();
                if(local == null){
                    local = new HashMap<>();
                }
                local.put(keyp,objp);
            };


}
