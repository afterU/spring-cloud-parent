package com.hzw.study.config.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${info.address}")
    private String address;

    @Autowired
    private Environment env;


    @RequestMapping("/cloud/address")
    public String url1(){
        return address;
    }
     @RequestMapping("/cloud/address2")
    public String url2() {
         return env.getProperty("info.address");
    }
}
