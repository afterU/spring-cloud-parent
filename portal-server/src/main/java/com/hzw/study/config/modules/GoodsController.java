package com.hzw.study.config.modules;

import com.hzw.study.config.feign.GoodsRemoteHystrixClient;
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

//    @Autowired
//    private GoodsRemoteClient goodsRemoteClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GoodsRemoteHystrixClient goodsRemoteHystrixClient;



    //rebbin+restTemplate方式调用远程服务
    @RequestMapping("/get")
    public String getGoods(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(goods_server, String.class);

        return forEntity.getBody();
    }

    //feign方式调用远程服务
//    @RequestMapping("/getFeign")
//    public String goodsFeign() {
//        //调用远程的一个controller, restful的调用
//        return goodsRemoteClient.goods();
//    }

    //hystrix注解方式
    @RequestMapping("/getHystrix")
    @HystrixCommand(fallbackMethod = "failMethod",commandProperties = {
    @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")})
//    ignoreExceptions = Throwable.class)
    public String goodsHystrix(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(goods_server, String.class);

        return forEntity.getBody();
    }

    public String failMethod(Throwable t){
        t.printStackTrace();
        return "hystrix";
    }


    //hystrix注解方式，ignoreExceptions 忽略异常之后就不会走降级方法，直接将上层调用抛出异常
    @RequestMapping("/getHystrixException")
    @HystrixCommand(fallbackMethod = "failMethodException",commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")},
    ignoreExceptions = Throwable.class)
    public String getHystrixException(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(goods_server, String.class);

        return forEntity.getBody();
    }

    public String failMethodException(Throwable t){
        t.printStackTrace();
        return "hystrixexception";
    }

    //hystrix的限流
    @RequestMapping("/getHystrixLimit")
    @HystrixCommand(fallbackMethod = "limitMethod",commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")},
            ignoreExceptions = Throwable.class,
    threadPoolKey = "goods",
    threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "2"),
            @HystrixProperty(name = "maxQueueSize",value = "1")
    })//核心线程数为2，阻塞队列最大为1，因此限流3，超过3，走降级方法
    public String getHystrixLimit(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(goods_server, String.class);

        return forEntity.getBody();
    }

    public String limitMethod(){
        return "limithistrix";
    }

    //feign+hystrix方式调用远程服务
    @RequestMapping("/getFeignHystrix")
    public String getFeignHystrix(){


        return goodsRemoteHystrixClient.getGoods();
    }


}
