package com.tolo.orderservice.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

@Data
public class Orders {

    @TableId("ORDERS_ID")
    private String ordersId;

    @TableField("USER_ID")
    private String userId;

    @TableField("PRODUCT_ID")
    private String productId;

}
