package com.project.ychengspmall.manager.aop;

import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author admin
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {


    @ExceptionHandler
    public Result handlerGlobalException(HttpServletResponse response, Exception e) {
        response.setContentType("application/json;charset=utf-8");
        log.error(e.getMessage());
        return Result.build(null, ResultCodeEnum.ERROR_BUSINESS.getCode(), e.getMessage());
    }


}
