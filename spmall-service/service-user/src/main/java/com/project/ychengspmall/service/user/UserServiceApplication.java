package com.project.ychengspmall.service.user;

import com.project.ychengspmall.common.service.anno.EnableUserLoginAuthInterceptor;
import com.project.ychengspmall.common.service.anno.EnableUserWebMvcConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author XxychengychengxX
 * @date 2024/02/03
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.project.ychengspmall.common.util","com.project.ychengspmall.service.user"})
@EnableUserLoginAuthInterceptor
@MapperScan(basePackages = "com.project.ychengspmall.service.user.mapper")
@ConfigurationPropertiesScan(basePackages = "com.project.ychengspmall.service.user.model.prop")
@EnableCaching
//@EnableUserWebMvcConfiguration
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }
}
