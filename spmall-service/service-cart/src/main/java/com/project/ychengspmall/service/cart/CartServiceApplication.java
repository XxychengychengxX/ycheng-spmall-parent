package com.project.ychengspmall.service.cart;

import com.project.ychengspmall.common.service.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 @author admin
 @Date 2024/1/21
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.project.ychengspmall.feign.product"})
@EnableUserLoginAuthInterceptor
public class CartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }
}
