package com.leaf.cloud.server.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.leaf.cloud"})
public class CloudServerBaseApplication {

    public static void main(String[] args) {

        SpringApplication.run(CloudServerBaseApplication.class, args);
        System.out.println("===> cloud-server-base 启动成功");
    }

}
