package com.project.ychengspmall.service.user.service;

import com.project.ychengspmall.model.dto.h5.UserLoginDto;
import com.project.ychengspmall.model.dto.h5.UserRegisterDto;
import com.project.ychengspmall.model.vo.h5.UserInfoVo;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    Object login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
