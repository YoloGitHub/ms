# 秒杀系统简述

    本系统仅用于练习，即对秒杀预减库存方案的模拟。
        
    使用SpringBoot + SpringCloud + MybatisPlus + Mysql/H2 + Redis + Redisson + RabbitMQ + Ider ID生成器；
    
## 架构图

![秒杀架构图](https://yologithub.github.io/pages/ms.jpg "秒杀架构")

# 初始化

## mysql建库、用户

    mysql
    create schema ms default character set utf8 collate utf8_general_ci;
    create user 'ms'@'%' identified by 'ms';
    create user 'ms'@'localhost' identified by 'ms';
    grant all on ms.* to ms;
    flush privileges;


## 表1 - 订单表

    CREATE TABLE ORDERS(
        ORDERS_ID VARCHAR(50) PRIMARY KEY,
        USER_ID VARCHAR(50),
        PRODUCT_ID VARCHAR(50)
    );

## init
### 测试用 
    [^_^]: insert into ORDERS values('1', 'wly', 'pen');


## 表2 - 库存表

    CREATE TABLE STOCK(
        STOCKS_ID VARCHAR(50) PRIMARY KEY,
        STOCK_NUM int
    );

## init

    insert into STOCK values('pen', 100);

## Redis init

    hset msProduct pen 100

## 配置文件地址替换

    全局替换配置文件中 YOUR_IP 为你的IP或者127.0.0.1

## Ider ID生成器Jar包获取

    地址：https://github.com/YoloGitHub/ider-spring-boot-starter

# 测试
 
    1. MsserviceApplicationTests.concurrentTest : 用于做并发测试
    2. MsserviceApplicationTests.reset : 用于重置环境
        a. redis的key重置为你设置的值
        b. 数据库表重置：ORDERS表全部删除、STOCK表重置为你设置的值
    
# 描述   

### 详细说明
    
    本系统分为4个微服务，分别是：
        订单服务（orderservice）、库存服务（stockservice）、Redis服务（redisservice）、秒杀服务（msservice）。
    流程描述：
        1. 秒杀请求过来，在秒杀服务中进行Redis服务调用预先存好的存储数据即Product中的pen的值，进行>0判断;
        2. 之后在Redis中预减库存，使用Redisson提供的分布式锁进行[判断+更新]排队处理；
        3. 将秒杀成功的数据放入RabbitMQ中；
        4. 订单服务订阅RabbitMQ消息，进行数据库订单生成，同时调用库存服务进行减库存处理，使用分布式事务进行事务化；
        5. 最后定时查看订单表支付状态，是否进行支付，超时则订单失效，库存回退处理；
        
    暂未实现： 
        本地缓存、分布式事务、超时订单。  
    
### 特例说明
    
    数据库表，为方便测试，都使用同一个库ms，并未再分别给Order库 和 Stock库分开，同时Id生成器也是ms库。
    
## 更详细说明，秒杀的关键点

    参见：doc/秒杀_V1.0.xlsx
    
## 参考说明

    一个秒杀系统的设计思考
        https://segmentfault.com/a/1190000020970562
    如何设计一个秒杀系统
        https://developer.aliyun.com/article/65129    
    大型网站技术架构之秒杀系统架构设计
        https://developer.aliyun.com/article/38090

# 联系

    姓名：王先生
    邮箱：wangliyun0987@outlook.com
    电话：13371943540