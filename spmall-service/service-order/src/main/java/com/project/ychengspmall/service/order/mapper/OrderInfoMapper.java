package com.project.ychengspmall.service.order.mapper;

import com.project.ychengspmall.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author XxychengychengxX
 * @date 2024/1/28
 */
@Mapper
public interface OrderInfoMapper {
    void save(OrderInfo orderInfo);

    OrderInfo getById(Long orderId);

    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
    OrderInfo getByOrderNo(String orderNo) ;

}
