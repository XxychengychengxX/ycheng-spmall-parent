package com.project.ychengspmall.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录请求参数
 */
@Data
@Schema(description = "用户登录请求参数")
public class LoginDto {


    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userName ;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password ;

    /**
     * 提交验证码
     */
    @Schema(description = "提交验证码")
    private String captcha ;

    /**
     * 验证码的key
     */
    @Schema(description = "验证码key")
    private String codeKey ;

}
