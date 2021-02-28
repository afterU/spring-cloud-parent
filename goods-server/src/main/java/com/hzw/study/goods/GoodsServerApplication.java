package com.hzw.study.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/2/28 14:21
 */

@SpringBootApplication
@EnableEurekaClient
public class GoodsServerApplication {
    // 启动时添加启动参数 --spring.profiles.active=8001
    public static void main(String[] args) {
        SpringApplication.run(GoodsServerApplication.class,args);
    }
}
