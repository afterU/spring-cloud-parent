package com.hzw.study.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/2/28 20:19
 */
@FeignClient("goods-server")
public interface GoodsRemoteClient {
    @RequestMapping("/goods/get")
    public String goods();
}
