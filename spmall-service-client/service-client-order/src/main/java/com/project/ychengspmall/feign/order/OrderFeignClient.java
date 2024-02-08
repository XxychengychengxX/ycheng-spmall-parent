package com.project.ychengspmall.feign.order;

import com.project.ychengspmall.model.entity.order.OrderInfo;
import com.project.ychengspmall.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@FeignClient
public interface OrderFeignClient {

    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo) ;

}
