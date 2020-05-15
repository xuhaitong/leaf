package com.leaf.cloud.config.auth.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.leaf.cloud.config.auth.base.Leaf;
import com.leaf.cloud.config.auth.thread.UserThreadLocalUtils;
import com.leaf.cloud.config.auth.tool.JwtTool;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class VerifyTokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(VerifyTokenInterceptor.class);

    @Autowired
    private JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String contentType = request.getContentType();

        String token ="";
        String userName = "";
        if(!StringUtils.isEmpty(method)){
            if("post".equals(method.toLowerCase()) && "application/json".equals(contentType)){
                String jsonBody = getRequestBodyString(request);
                JSONObject json = JSONObject.parseObject(jsonBody);
                token = json.getString("token");
            }else if("get".equals(method.toLowerCase())){
                token = request.getParameter("token");
            }
            if(StringUtils.isEmpty(token)){
                logger.error("==非法请求，token缺失==");
                response.sendError(1001,"res.getMessage()");
                return false;
            }
            Leaf res = jwtTool.verifyUserToken(token);
            if(res.isSuccess()){
                UserThreadLocalUtils.setToken(token);
                UserThreadLocalUtils.setUser((String)res.getData());
                logger.info("token 验证 sub = {},--{}",res.getData(),"shit");
            }else{
                logger.info("token 验证失败 {},---{}",res.getData(),"shit");
                response.sendError(1001,res.getMessage());
                return false;
            }

        }else{
            logger.error("非法请求");
            response.sendError(1001,"非法请求");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        VerifyTokenInterceptor interceptor = new VerifyTokenInterceptor();
//        Comparator
//        interceptor.p
    }

    public static String getRequestBodyString(HttpServletRequest request)
    {
        InputStream ins = null;
        BufferedReader bufferReader = null;
        StringBuffer sb = new StringBuffer();
        try {
            ins = request.getInputStream();
            bufferReader = new BufferedReader(new InputStreamReader(ins, Charsets.UTF_8));

            String line = null;
            while ((line = bufferReader.readLine()) != null)
                sb.append(line);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
                if (bufferReader != null)
                    bufferReader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
