package com.project.ychengspmall.common.service.exception;

import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class YchengException extends RuntimeException {

    // 错误状态码
    private Integer code ;
    // 错误消息
    private String message ;
    // 封装错误状态码和错误消息
    private ResultCodeEnum resultCodeEnum ;

    public YchengException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public YchengException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
