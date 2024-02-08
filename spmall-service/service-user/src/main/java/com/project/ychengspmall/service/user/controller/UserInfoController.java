package com.project.ychengspmall.service.user.controller;

import com.project.ychengspmall.model.dto.h5.UserLoginDto;
import com.project.ychengspmall.model.dto.h5.UserRegisterDto;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.h5.UserInfoVo;
import com.project.ychengspmall.service.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/api/user/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    /**
     * 会员注册
     * @param userRegisterDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }


    @GetMapping("/logout")
    public Result logout(@RequestHeader("Token") String token) {
        userInfoService.logout(token);
        return Result.build(null , ResultCodeEnum.SUCCESS);

    }
    /**
     * 会员登录
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return Result.build(userInfoService.login(userLoginDto), ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取当前登录用户信息
     * @param token 请求头中携带的token
     * @return
     */
    @GetMapping("/auth/getCurrentUserInfo")
    public Result<UserInfoVo> getCurrentUserInfo(@RequestHeader("Token") String token) {

        UserInfoVo userInfoVo = userInfoService.getCurrentUserInfo(token) ;
        return Result.build(userInfoVo , ResultCodeEnum.SUCCESS) ;
    }
}
