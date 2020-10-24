package com.tolo.redisservice.controller;

import com.tolo.orderservice.client.OrderServiceClient;
import com.tolo.redisservice.client.OtherServiceClient;
import com.tolo.redisservice.mapper.TestMapper;
import com.tolo.redisservice.po.Test;
import com.tolo.stockservice.client.StockServiceClient;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisserviceController {

//    @Autowired
//    public OtherServiceClient osc;

//    @Resource
//    public TestMapper testMapper;


    private static final String MS_PRODUCT_HASH = "msProduct";

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StockServiceClient stockServiceClient;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @GetMapping("/getStockFromRedis")
    public Integer getStockFromRedis(String productId){

//        redissonClient.getMap(MS_PRODUCT_HASH).put("pen", 100);
        Integer num = (Integer) redissonClient.getMap(MS_PRODUCT_HASH).get(productId);

        return num;
    }

    @GetMapping("/setStockFromRedis")
    public Integer setStockFromRedis(String productId, Integer value){

        Integer result = (Integer) redissonClient.getMap(MS_PRODUCT_HASH).put(productId, value);
        return result;
    }

    @GetMapping("reset")
    public String resetForTest(Integer num){

        redissonClient.getMap(MS_PRODUCT_HASH).put("pen", num);
        //reset数据库数据
        Integer result = (Integer) stockServiceClient.resetForTest(num);

        Integer resultOrderNum = orderServiceClient.resetForTest();

        return "reset ok : " + resultOrderNum;
    }

//    @GetMapping("/testMapper")
//    public String testMapper(){
//        Test test = testMapper.selectById("1");
//        return test.toString();
//    }
}
