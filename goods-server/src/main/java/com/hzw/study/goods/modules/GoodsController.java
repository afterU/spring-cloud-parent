package com.hzw.study.goods.modules;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description
 * @createTime 2021/2/28 15:24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController implements ApplicationContextAware {


    private ApplicationContext annotationConfigApplicationContext;
    @RequestMapping("/get")
    public String getGoods(){
        int i= 1/0;
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String port = annotationConfigApplicationContext.getEnvironment().getActiveProfiles()[0];
        System.out.println(port+" 服务执行了........");
        return "iphone";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        annotationConfigApplicationContext = applicationContext;
    }
}
