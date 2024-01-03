package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.dto.order.OrderStatisticsDto;
import com.project.ychengspmall.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {
    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
