package com.project.ychengspmall.service.pay.controller;

import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.service.pay.service.AlipayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 @author XxychengychengxX
 @date 2024/1/28
 */

@Controller
@RequestMapping("/api/order/alipay")
public class AlipayController {

    @Resource
    private AlipayService alipayService;

    /**
     * 支付宝下单
     * @param orderNo 订单号
     * @return
     */
    @GetMapping("submitAlipay/{orderNo}")
    @ResponseBody
    public Result<String> submitAlipay(@PathVariable(value = "orderNo") String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }
}
