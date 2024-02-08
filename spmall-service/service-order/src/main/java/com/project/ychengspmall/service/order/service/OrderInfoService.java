package com.project.ychengspmall.service.order.service;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.h5.OrderInfoDto;
import com.project.ychengspmall.model.entity.order.OrderInfo;
import com.project.ychengspmall.model.vo.h5.TradeVo;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo) ;

}
