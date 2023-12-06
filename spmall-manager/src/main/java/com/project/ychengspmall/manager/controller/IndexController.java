package com.project.ychengspmall.manager.controller;

import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.manager.service.SysUserService;
import com.project.ychengspmall.manager.service.ValidationCodeService;
import com.project.ychengspmall.model.dto.system.LoginDto;
import com.project.ychengspmall.model.entity.system.SysUser;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.system.LoginVo;
import com.project.ychengspmall.model.vo.system.ValidateCodeVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 登录界面请求
 */
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Resource
    SysUserService userService;

    @Resource
    ValidationCodeService validateCodeService;

    /**
     * 获取用户信息
     *
     * @param token 登陆后的token令牌
     * @return
     */
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token) {
        SysUser sysUser = AuthContextUtil.get();
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户登出
     *
     * @param token 用户token
     * @return
     */
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        userService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取验证码
     *
     * @return Result对象2
     */
    @GetMapping(value = "/generateValidateCode")
    public Result generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户登录
     *
     * @param loginDto web请求参数
     * @return Result对象
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = userService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
