package com.leaf.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.leaf.mybatis.autoconfigure.MybatisAutoConfiguration;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@ComponentScan
@MapperScan("com.leaf.service")
@Order(3)
public class ServiceAutoConfiguration {

	public static void main(String[] args) {
		System.out.println("AutoConfiguration:service start ===========");
	}
}
