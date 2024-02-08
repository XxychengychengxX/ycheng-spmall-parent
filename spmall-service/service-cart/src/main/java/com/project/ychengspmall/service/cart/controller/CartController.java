package com.project.ychengspmall.service.cart.controller;

import com.project.ychengspmall.model.entity.h5.CartInfo;
import com.project.ychengspmall.model.vo.common.Result;
import com.project.ychengspmall.model.vo.common.ResultCodeEnum;
import com.project.ychengspmall.service.cart.service.CartService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@RestController
@RequestMapping("/api/order/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 查询购物车
     * @return
     */
    @GetMapping("/auth/cartList")
    public Result<List<CartInfo>> cartList() {
        List<CartInfo> cartInfoList = cartService.getCartList();
        return Result.build(cartInfoList, ResultCodeEnum.SUCCESS);
    }
    /**
     * 添加到购物车
     * @param skuId 商品skuId
     * @param skuNum 数量
     * @return
     */
    @GetMapping("auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart( @PathVariable("skuId") Long skuId,
                             @PathVariable("skuNum") Integer skuNum) {
        cartService.addToCart(skuId, skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 清空购物车
     * @return
     */
    @GetMapping(value = "/auth/deleteChecked")
    public Result deleteChecked() {
        cartService.deleteChecked() ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 删除购物车
     * @param skuId 商品skuId
     * @return
     */
    @DeleteMapping("auth/deleteCart/{skuId}")
    public Result deleteCart(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable(
            "skuId") Long skuId) {
        cartService.deleteCart(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新购物车状态
     * @param skuId 商品skuId
     * @param isChecked 是否选中 1:选中 0:取消选中
     * @return
     */
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable(value = "skuId") Long skuId,
                            @Parameter(name = "isChecked", description = "是否选中 1:选中 0:取消选中", required = true) @PathVariable(value = "isChecked") Integer isChecked) {
        cartService.checkCart(skuId, isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新购物车商品全部选中状态
     * @param isChecked 是否选中 1:选中 0:取消选中
     * @return
     */
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@Parameter(name = "isChecked", description = "是否选中 1:选中 0:取消选中", required = true) @PathVariable(value = "isChecked") Integer isChecked){
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 清空购物车
     * @return
     */
    @GetMapping("/auth/clearCart")
    public Result clearCart(){
        cartService.clearCart();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "/auth/getAllCkecked")
    public List<CartInfo> getAllCkecked() {
        List<CartInfo> cartInfoList = cartService.getAllChecked() ;
        return cartInfoList;
    }
}
