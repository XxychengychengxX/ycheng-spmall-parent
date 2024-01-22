package com.project.ychengspmall.service.user;

import com.project.ychengspmall.common.service.anno.EnableUserLoginAuthInterceptor;
import com.project.ychengspmall.common.service.anno.EnableUserWebMvcConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.ychengspmall.common.util")
@EnableUserLoginAuthInterceptor
@MapperScan(basePackages = "com.project.ychengspmall.service.user.mapper")
@ConfigurationPropertiesScan(basePackages = "com.project.ychengspmall.service.user.model.prop")
@EnableUserWebMvcConfiguration
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }
}
