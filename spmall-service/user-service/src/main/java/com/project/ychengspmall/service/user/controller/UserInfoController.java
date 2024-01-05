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

@Tag(name = "会员用户接口")
@RestController
@RequestMapping("api/user/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @Operation(summary = "会员注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "会员登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return Result.build(userInfoService.login(userLoginDto), ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("auth/getCurrentUserInfo")
    public Result<UserInfoVo> getCurrentUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoVo userInfoVo = userInfoService.getCurrentUserInfo(token) ;
        return Result.build(userInfoVo , ResultCodeEnum.SUCCESS) ;
    }
}
