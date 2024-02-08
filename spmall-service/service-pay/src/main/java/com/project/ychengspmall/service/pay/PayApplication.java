package com.project.ychengspmall.service.pay;

import com.project.ychengspmall.common.service.anno.EnableUserWebMvcConfiguration;
import com.project.ychengspmall.service.pay.property.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
@ConfigurationPropertiesScan
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }
}
