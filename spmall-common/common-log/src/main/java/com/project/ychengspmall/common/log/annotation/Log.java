package com.project.ychengspmall.common.log.annotation;

import com.project.ychengspmall.common.log.enums.OperatorType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {		// 自定义操作日志记录注解

    @AliasFor("description")
    String title();								// 模块名称
    @AliasFor("title")
    String description();
    
    OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别
    int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）
    boolean isSaveRequestData() default true;   // 是否保存请求的参数
    boolean isSaveResponseData() default true;  // 是否保存响应的参数

}
