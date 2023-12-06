package com.project.ychengspmall.manager.config;

import com.project.ychengspmall.manager.interceptor.AuthContextInterceptor;
import com.project.ychengspmall.manager.properties.UserAuthProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    AuthContextInterceptor authContextInterceptor;
    @Resource
    UserAuthProperties userAuthProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authContextInterceptor)
                //排除登录，登出。获取验证码的接口
                .excludePathPatterns(userAuthProperties.getNoConfirmUrls())
                .addPathPatterns("/**");
    }
}
