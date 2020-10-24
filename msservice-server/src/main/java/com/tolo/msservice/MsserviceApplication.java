package com.tolo.msservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.tolo.redisservice.client"})
@RestController
@MapperScan("com.tolo.msservice.mapper")
public class MsserviceApplication {

    @RequestMapping("/actuator/health")
    public String home(){
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(MsserviceApplication.class, args);
    }

}
