package com.hzw.study.portal.modules;

import com.hzw.study.portal.feign.GoodsRemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/2/28 15:24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private String goods_server = "http://GOODS-SERVER/goods/get";

    @Autowired
    private GoodsRemoteClient goodsRemoteClient;

    @Autowired
    private RestTemplate restTemplate;

    //rebbin+restTemplate方式调用远程服务
    @RequestMapping("/get")
    public String getGoods(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(goods_server, String.class);

        return forEntity.getBody();
    }

    //feign方式调用远程服务
    @RequestMapping("/getFeign")
    public String goodsFeign() {
        //调用远程的一个controller, restful的调用
        return goodsRemoteClient.goods();
    }

    //hystrix注解方式
    @RequestMapping("/getHystrix")
    @HystrixCommand(fallbackMethod = "failMethod",commandProperties = {
    @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")})
    public String goodsHystrix(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(goods_server, String.class);

        return forEntity.getBody();
    }

    public String failMethod(){
        return "hystrix";
    }
}
