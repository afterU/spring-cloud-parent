package com.hzw.study.portal.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @LoadBalanced  //使用Ribbon实现负载均衡的调用 (spring cloud -> 封装ribbon + eureka + restTemplate)
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }


}