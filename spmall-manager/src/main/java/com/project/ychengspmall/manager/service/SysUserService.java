package com.project.ychengspmall.manager.service;


import com.project.ychengspmall.model.dto.system.LoginDto;
import com.project.ychengspmall.model.vo.system.LoginVo;

public interface SysUserService {
    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);
}

