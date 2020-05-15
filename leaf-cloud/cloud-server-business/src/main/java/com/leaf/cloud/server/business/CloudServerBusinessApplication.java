package com.leaf.cloud.server.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.leaf.cloud"})
public class CloudServerBusinessApplication {

    public static void main(String[] args) {

        SpringApplication.run(CloudServerBusinessApplication.class, args);
        System.out.println("===> cloud-server-business 启动成功");
    }

}
