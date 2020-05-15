package com.leaf.cloud.config.auth.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Autowired
    private VerifyTokenInterceptor verifyTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        logger.info("注册拦截器");
        registry
                .addInterceptor(verifyTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login");
//        ThreadPoolExecutor

    }

//    configureContentNegotiation
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//
//    }
}
