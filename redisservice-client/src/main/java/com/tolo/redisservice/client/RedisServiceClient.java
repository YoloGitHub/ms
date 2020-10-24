package com.tolo.redisservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "redisservice")
public interface RedisServiceClient {

    @GetMapping("/getStockFromRedis")
    public Integer getStockFromRedis(@RequestParam String productId);

    @GetMapping("/setStockFromRedis")
    public Integer setStockFromRedis(@RequestParam String productId, @RequestParam Integer value);
}
