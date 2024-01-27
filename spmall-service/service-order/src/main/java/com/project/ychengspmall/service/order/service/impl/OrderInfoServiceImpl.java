package com.project.ychengspmall.service.order.service.impl;

import com.project.ychengspmall.feign.cart.CartFeignClient;
import com.project.ychengspmall.model.entity.h5.CartInfo;
import com.project.ychengspmall.model.entity.order.OrderItem;
import com.project.ychengspmall.model.vo.h5.TradeVo;
import com.project.ychengspmall.service.order.service.OrderInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    CartFeignClient cartFeignClient;

    @Override
    public TradeVo getTrade() {
        // 获取当前登录的用户的id
        //Long userId = AuthContextUtil.getUserInfo().getId();

        // 获取选中的购物项列表数据
        List<CartInfo> cartInfoList = cartFeignClient.getAllChecked();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {        // 将购物项数据转换成功订单明细数据
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        // 计算总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }
}
