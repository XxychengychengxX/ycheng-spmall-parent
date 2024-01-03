package com.project.ychengspmall.manager.service;

import com.project.ychengspmall.model.dto.order.OrderStatisticsDto;
import com.project.ychengspmall.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);

}
