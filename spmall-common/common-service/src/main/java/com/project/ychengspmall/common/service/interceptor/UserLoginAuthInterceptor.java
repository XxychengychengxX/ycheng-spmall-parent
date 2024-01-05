package com.project.ychengspmall.common.service.interceptor;

import com.alibaba.fastjson.JSON;
import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.model.entity.user.UserInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果token不为空，那么此时验证token的合法性
        String userInfoJSON = redisTemplate.opsForValue()
                .get("user:spmall:" + request.getHeader("token"));
        AuthContextUtil.setUserInfo(JSON.parseObject(userInfoJSON, UserInfo.class));
        return true;

    }
}
