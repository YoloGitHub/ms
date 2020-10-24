package com.tolo.stockservice.controller;

import com.tolo.stockservice.mapper.StockMapper;
import com.tolo.stockservice.po.Stock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StockserviceController {

//    @Autowired
//    public OtherServiceClient osc;

    @Resource
    public StockMapper stockMapper;

//    @RequestMapping("/callProduct")
//    public String getProduct(){
//        return osc.productMsg();
//    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(String productId){

        Stock stock = stockMapper.selectById(productId);
        if(stock.getStockNum()>0){
            stock.setStockNum(stock.getStockNum() -1);
        }
        stockMapper.updateById(stock);
        return stock.toString();
    }

    @GetMapping("/reset")
    public Integer resetForTest(Integer num){

        Stock stock = new Stock();
        stock.setProductId("pen");
        stock.setStockNum(num);

        Integer result = stockMapper.updateById(stock);

        return result;
    }
}
