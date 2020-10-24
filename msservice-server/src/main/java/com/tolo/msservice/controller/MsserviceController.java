package com.tolo.msservice.controller;

import com.tolo.orderservice.po.Orders;
import com.tolo.redisservice.client.RedisServiceClient;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsserviceController {

    @Autowired
    public RedisServiceClient redisServiceClient;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/ms")
    public String ms(String userId, String productId){

        RLock rLock = redissonClient.getLock(productId);
        rLock.lock();

        try{
            Integer num = redisServiceClient.getStockFromRedis(productId);
            if(num > 0){
                redisServiceClient.setStockFromRedis(productId, num - 1);
            } else {
                return "over";
            }

            // 放入MQ，这里如果修改Redis成功，放入MQ失败了呢？ 这里就使用最终确定，即最终支付是直接查询数据库，如果没有订单，那还是说明失败了，所以这里不好先把结果预存，预存的话，那可能明明秒杀提示成功，但是最终支付的时候又告知没有成功；

            Orders order = new Orders();
            order.setUserId(userId);
            order.setProductId(productId);
            amqpTemplate.convertAndSend("msQueue",order);

        }finally {
            if(rLock != null)
                rLock.unlock();
        }
        return "got";
    }

}
