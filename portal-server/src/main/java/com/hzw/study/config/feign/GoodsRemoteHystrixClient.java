package com.hzw.study.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/3/6 18:54
 */
@FeignClient(value = "goods-server",fallback = GoodsRemoteHystrixClientFallBack.class)
//@FeignClient(value = "goods-server",fallbackFactory = GoodsRemoteHystrixClientFallBackFactory.class)
public interface GoodsRemoteHystrixClient {

    @RequestMapping("/goods/get")
    public String getGoods();
}
