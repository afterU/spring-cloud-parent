package com.hzw.study.config.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/3/6 18:54
 */
@Component
public class GoodsRemoteHystrixClientFallBackFactory implements FallbackFactory<GoodsRemoteHystrixClient> {

    @Override
    public GoodsRemoteHystrixClient create(Throwable throwable) {
        return new GoodsRemoteHystrixClient() {
            @Override
            public String getGoods() {
                throwable.printStackTrace();
                return "feignhystrix";
            }
        };
    }
}
