package com.tolo.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orderservice")
public interface OrderServiceClient {

    @GetMapping("/reset")
    Integer resetForTest();
}
