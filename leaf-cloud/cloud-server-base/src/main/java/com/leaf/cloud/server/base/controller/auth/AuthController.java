package com.leaf.cloud.server.base.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.leaf.cloud.config.auth.thread.UserThreadLocalUtils;
import com.leaf.cloud.config.auth.tool.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtTool jwtTool;
    @RequestMapping
    public String init(){
        return "auth";
    }

    @RequestMapping("login")
    public String login(String userName,String pwd){
        String token = this.jwtTool.createUserToken(userName);
        return token;
    }

    @RequestMapping("verify")
    public String verify(String token,String userName){
        String tokenLocal = UserThreadLocalUtils.getToken();
        Object userLocal = UserThreadLocalUtils.getUser();
        Map localMap = UserThreadLocalUtils.getAll();
        return JSONObject.toJSONString(localMap);

        //return this.jwtTool.verifyUserToken(token,userName);
    }
}
