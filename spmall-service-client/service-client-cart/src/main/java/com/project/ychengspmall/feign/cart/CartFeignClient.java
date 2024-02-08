package com.project.ychengspmall.feign.cart;

import com.project.ychengspmall.model.entity.h5.CartInfo;
import com.project.ychengspmall.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    List<CartInfo> getAllChecked();


    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    Result deleteChecked() ;

}
