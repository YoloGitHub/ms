package com.tolo.orderservice.configuration;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.tolo.idFactory.impl.step.StepIdFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@PropertySource("classpath:fx.properties")
@SpringBootConfiguration
public class IdFactoryConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "step-id-factory")
    public StepIdFactory stepIdFactory(ApplicationContext applicationContext){

        StepIdFactory stepIdFactory = new StepIdFactory();
        stepIdFactory.setDataSource(applicationContext.getBean(DataSource.class));

        return stepIdFactory;
    }
}
