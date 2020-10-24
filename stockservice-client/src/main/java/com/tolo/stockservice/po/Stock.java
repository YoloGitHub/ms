package com.tolo.stockservice.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

@Data
public class Stock {

    @TableId("PRODUCT_ID")
    private String productId;

    @TableField("STOCK_NUM")
    private Integer stockNum;

}
