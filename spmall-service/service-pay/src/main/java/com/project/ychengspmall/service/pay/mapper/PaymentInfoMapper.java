package com.project.ychengspmall.service.pay.mapper;

import com.project.ychengspmall.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@Mapper
public interface PaymentInfoMapper {
    PaymentInfo getByOrderNo(String orderNo);

    void save(PaymentInfo paymentInfo);
}
