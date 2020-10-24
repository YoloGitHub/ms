package com.tolo.stockservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stockservice")
public interface StockServiceClient {

    @GetMapping("/decreaseStock")
    String decreaseStock(@RequestParam String productId);

    @GetMapping("/reset")
    Integer resetForTest(@RequestParam Integer num);
}
