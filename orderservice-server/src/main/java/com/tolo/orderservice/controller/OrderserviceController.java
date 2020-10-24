package com.tolo.orderservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tolo.idFactory.impl.step.StepIdFactory;
import com.tolo.orderservice.mapper.OrdersMapper;
import com.tolo.orderservice.po.Orders;
import com.tolo.stockservice.client.StockServiceClient;
import com.tolo.stockservice.po.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderserviceController {

    @Autowired
    public StepIdFactory stepIdFactory;

    @Autowired
    public StockServiceClient stockServiceClient;

    @Resource
    public OrdersMapper ordersMapper;

//    @RequestMapping("/callProduct")
//    public String getProduct(){
//        return osc.productMsg();
//    }

    @GetMapping("/createOrder")
    public String createOrder(Orders orders){

        orders.setOrdersId(stepIdFactory.getFactory("orders").generateString());
        orders.setUserId(orders.getUserId());
        orders.setProductId(orders.getProductId());
        ordersMapper.insert(orders);

        //同时分布式事务，扣减库存
        stockServiceClient.decreaseStock(orders.getProductId());

        return orders.toString();
    }


    @GetMapping("/reset")
    public Integer resetForTest(){

        QueryWrapper<Orders> queryWrapper = new QueryWrapper<Orders>();
        queryWrapper.select("productId", "pen");
        Integer result = ordersMapper.delete(queryWrapper);

        return result;
    }
}
