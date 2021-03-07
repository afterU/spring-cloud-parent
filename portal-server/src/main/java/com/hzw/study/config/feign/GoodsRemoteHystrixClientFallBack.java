package com.hzw.study.config.feign;

import org.springframework.stereotype.Component;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/3/6 18:54
 */
@Component
public class GoodsRemoteHystrixClientFallBack implements GoodsRemoteHystrixClient{
    @Override
    public String getGoods() {

        return "feginhystrix";
    }
}
