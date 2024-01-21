package com.project.ychengspmall.manager.task;

import cn.hutool.core.date.DateUtil;
import com.project.ychengspmall.manager.mapper.OrderInfoMapper;
import com.project.ychengspmall.manager.mapper.OrderStatisticsMapper;
import com.project.ychengspmall.model.entity.order.OrderStatistics;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class OrderStatisticTask {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;

    /**
     * 每天凌晨2点执行一次
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1)
                .toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if (orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
