package com.leaf.cloud.server.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.leaf.cloud.config.auth.thread.UserThreadLocalUtils;
import com.leaf.cloud.config.auth.tool.JwtTool;
import jdk.nashorn.internal.ir.FunctionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private JwtTool jwtTool;

    /*@RequestMapping("verify")
    public String verify(String token,String userName){

        return this.jwtTool.verifyUserToken(token,userName);

    }*/
    @RequestMapping("verify")
    public String verify(String token,String userName){
        String tokenLocal = UserThreadLocalUtils.getToken();
        Object userLocal = UserThreadLocalUtils.getUser();
        Map localMap = UserThreadLocalUtils.getAll();
        return JSONObject.toJSONString(localMap);
    }

    public static void main(String[] args) {
//        Consumer
        Predicate<String> p = o -> o.equals("test");
        Predicate<String> g = o -> o.startsWith("t");
        System.out.println(p.and(g).negate().test("test"));

        Function<Object,String>[] arr = new Function[]{Object::toString};

//        String.format()
//        Stream
    }
}

