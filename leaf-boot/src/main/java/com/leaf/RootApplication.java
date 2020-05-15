package com.leaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan(basePackages= {"com.leaf.boot","com.leaf.service"})
// @MapperScan(basePackages= {"com.leaf.service"})
public class RootApplication {
	// MybatisAutoConfiguration ab = new MybatisAutoConfiguration();
	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}
}
