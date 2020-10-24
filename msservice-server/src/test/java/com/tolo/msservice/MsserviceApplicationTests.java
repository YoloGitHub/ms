package com.tolo.msservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class MsserviceApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void concurrentTest(){

        for(int i=0;i<40;i++){

            new Thread(()->{
                String result = restTemplate.getForObject("http://127.0.0.1:8085/ms?userId=wly"
                                + Thread.currentThread().getId() + "&productId=pen", String.class);
                System.out.println(result);
            }).start();
        }
    }


    @Test
    void reset() {
        String result = restTemplate.getForObject("http://127.0.0.1:8084/reset?num=125"
                , String.class);
        System.out.println(result);
    }

}
