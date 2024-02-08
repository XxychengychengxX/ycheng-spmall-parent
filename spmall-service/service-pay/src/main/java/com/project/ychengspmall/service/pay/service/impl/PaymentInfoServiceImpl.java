package com.project.ychengspmall.service.pay.service.impl;

import com.project.ychengspmall.feign.order.OrderFeignClient;
import com.project.ychengspmall.model.entity.order.OrderInfo;
import com.project.ychengspmall.model.entity.order.OrderItem;
import com.project.ychengspmall.model.entity.pay.PaymentInfo;
import com.project.ychengspmall.service.pay.mapper.PaymentInfoMapper;
import com.project.ychengspmall.service.pay.service.PaymentInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@Service
@Slf4j
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Resource
    private PaymentInfoMapper paymentInfoMapper;

    @Resource
    private OrderFeignClient orderFeignClient;

    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        // 查询支付信息数据，如果已经已经存在了就不用进行保存(一个订单支付失败以后可以继续支付)
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);
        if (null == paymentInfo) {
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            StringBuilder content = new StringBuilder();
            for (OrderItem item : orderInfo.getOrderItemList()) {
                content.append(item.getSkuName()).append(" ");
            }
            paymentInfo.setContent(content.toString());
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }
}
