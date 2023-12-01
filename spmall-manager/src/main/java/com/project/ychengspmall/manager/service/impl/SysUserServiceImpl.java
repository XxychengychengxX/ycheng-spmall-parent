package com.project.ychengspmall.manager.service.impl;

import com.project.ychengspmall.manager.service.SysUserService;
import com.project.ychengspmall.model.dto.system.LoginDto;
import com.project.ychengspmall.model.vo.system.LoginVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {


    @Resource
    StringRedisTemplate redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        return null;
    }
}
