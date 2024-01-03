package com.project.ychengspmall.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.project.ychengspmall.manager.mapper.OrderStatisticsMapper;
import com.project.ychengspmall.manager.service.OrderInfoService;
import com.project.ychengspmall.model.dto.order.OrderStatisticsDto;
import com.project.ychengspmall.model.entity.order.OrderStatistics;
import com.project.ychengspmall.model.vo.order.OrderStatisticsVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {

        // 查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(
                orderStatisticsDto);

        //日期列表
        List<String> dateList = orderStatisticsList.stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(),
                        "yyyy-MM-dd")).collect(Collectors.toList());

        //统计金额列表
        List<BigDecimal> amountList = orderStatisticsList.stream()
                .map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        // 返回数据
        return orderStatisticsVo;
    }
}
