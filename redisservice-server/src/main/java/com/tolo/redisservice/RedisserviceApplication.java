package com.tolo.redisservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.tolo.orderservice.client", "com.tolo.stockservice.client"})
@RestController
@MapperScan("com.tolo.redisservice.mapper")
public class RedisserviceApplication {

    @RequestMapping("/actuator/health")
    public String home(){
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisserviceApplication.class, args);
    }

}
