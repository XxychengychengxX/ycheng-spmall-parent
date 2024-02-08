package com.project.ychengspmall.service.order.mapper;

import com.project.ychengspmall.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@Mapper
public interface OrderItemMapper {
    void save(OrderItem orderItem);

    List<OrderItem> findByOrderId(Long id);
}
