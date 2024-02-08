package com.project.ychengspmall.common.service.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.model.entity.user.UserInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果token不为空，那么此时验证token的合法性
        String token = request.getHeader("Token");
        //只是把token从redis中获取出来，然后放到AuthContextUtil中
        if (StrUtil.isNotEmpty(token)) {

            String userinfojson = stringRedisTemplate.opsForValue().get("user:spmall:" + token);
            if (StrUtil.isNotEmpty(userinfojson)){
                AuthContextUtil.setUserInfo(JSON.parseObject(userinfojson, UserInfo.class));
            }
        }
        return true;

    }
}
