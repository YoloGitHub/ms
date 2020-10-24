package com.tolo.orderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.tolo.stockservice.client"})
@RestController
@MapperScan("com.tolo.orderservice.mapper")
public class OrderserviceApplication {

    @RequestMapping("/actuator/health")
    public String home(){
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderserviceApplication.class, args);
    }

}
