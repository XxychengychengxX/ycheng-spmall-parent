package com.project.ychengspmall.service.order;

import com.project.ychengspmall.common.service.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.project.ychengspmall.feign.cart"})
@EnableUserTokenFeignInterceptor
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
