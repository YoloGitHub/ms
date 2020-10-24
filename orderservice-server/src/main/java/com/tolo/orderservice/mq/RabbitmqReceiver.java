package com.tolo.orderservice.mq;

import com.tolo.orderservice.controller.OrderserviceController;
import com.tolo.orderservice.po.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq接收方
 */
@Slf4j
@Component
public class RabbitmqReceiver {

    @Autowired
    private OrderserviceController orderserviceController;

    @RabbitListener(queuesToDeclare = @Queue("msQueue"))
    public void msQueue(Orders orders){

        orderserviceController.createOrder(orders);
        log.info("MqReceiver : " + orders);
    }
}
