package com.project.ychengspmall.service.order.controller;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.h5.OrderInfoDto;
import com.project.ychengspmall.model.entity.order.OrderInfo;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.model.vo.h5.TradeVo;
import com.project.ychengspmall.service.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */

@RestController
@RequestMapping(value = "/api/order/orderInfo")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 点击立即购买获取订单
     * @param skuId 购买的商品型号sku
     * @return
     */
    @GetMapping("/auth/buy/{skuId}")
    public Result<TradeVo> buy(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 分页获取用户个人订单
     * @param page 当前页数
     * @param limit 每页记录数
     * @param orderStatus 订单状态
     * @return
     */
    @GetMapping("/auth/{page}/{limit}")
    public Result<PageInfo<OrderInfo>> list(@PathVariable Integer page, @PathVariable Integer limit,
                                            @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.findUserPage(page, limit, orderStatus);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取支付信息
     * @param orderNo
     * @return
     */
    @GetMapping("/auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo( @PathVariable String orderNo) {
        OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo) ;
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }
    /**
     * 获取订单详细信息
     * @param orderId 订单id
     * @return
     */
    @GetMapping("/auth/{orderId}")
    public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 确认下单
     * @return
     */
    @GetMapping("/auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }


    /**
     * 提交订单
     * @param orderInfoDto 前端订单信息
     * @return
     */
    @PostMapping("/auth/submitOrder")
    public Result<Long> submitOrder(@Parameter(name = "orderInfoDto", description = "请求参数实体类", required = true) @RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }
}
