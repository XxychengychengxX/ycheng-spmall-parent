package com.project.ychengspmall.service.cart.service;

import com.project.ychengspmall.model.entity.h5.CartInfo;

import java.util.List;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();

    List<CartInfo> getAllChecked();

    void deleteChecked();

    List<CartInfo> getCartList();

}
