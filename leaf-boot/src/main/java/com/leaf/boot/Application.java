package com.leaf.boot;

import com.leaf.log.EnableLeafLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLeafLog
@SpringBootApplication
// @ComponentScan(basePackages= {"com.leaf.boot","com.leaf.service"})
// @MapperScan(basePackages= {"com.leaf.service"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
