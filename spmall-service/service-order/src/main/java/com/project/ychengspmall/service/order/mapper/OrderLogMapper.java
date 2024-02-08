package com.project.ychengspmall.service.order.mapper;

import com.project.ychengspmall.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */
@Mapper
public interface OrderLogMapper {
    void save(OrderLog orderLog);
}
