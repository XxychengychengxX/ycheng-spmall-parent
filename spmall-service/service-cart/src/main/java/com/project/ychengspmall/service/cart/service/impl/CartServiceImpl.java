package com.project.ychengspmall.service.cart.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.project.ychengspmall.common.util.AuthContextUtil;
import com.project.ychengspmall.feign.product.ProductFeignClient;
import com.project.ychengspmall.model.entity.h5.CartInfo;
import com.project.ychengspmall.model.entity.product.ProductSku;
import com.project.ychengspmall.service.cart.service.CartService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 @author XxychengychengxX
 @Date 2024/1/21
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public void addToCart(Long skuId, Integer skuNum) {


        // 获取当前登录用户的id
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //获取缓存对象
        Object cartInfoObj = stringRedisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null;
        if (cartInfoObj != null) {       //  如果购物车中有该商品，则商品数量 相加！
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {

            // 当购物车中没用该商品的时候，则直接添加到购物车！
            cartInfo = new CartInfo();

            // 购物车数据是从商品详情得到 {skuInfo}
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());

        }
        stringRedisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));

    }

    @Override
    public void deleteCart(Long skuId) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //获取缓存对象
        if (StrUtil.isNotEmpty(cartKey)) {
            stringRedisTemplate.opsForHash().delete(cartKey, String.valueOf(skuId));
        }
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {

        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        if (StrUtil.isNotEmpty(cartKey)) {
            Boolean hasKey = stringRedisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
            if (hasKey) {
                String cartInfoJson = stringRedisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
                CartInfo cartInfo = JSON.parseObject(cartInfoJson, CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                stringRedisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
            }
        }


    }

    @Override
    public void allCheckCart(Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        // 获取所有的购物项数据
        List<Object> objectList = stringRedisTemplate.opsForHash().values(cartKey);
        if (!CollUtil.isEmpty(objectList)) {
            objectList.stream().map(cartInfoJson -> {
                CartInfo cartInfo = JSON.parseObject(cartInfoJson.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                return cartInfo;
            }).forEach(cartInfo -> stringRedisTemplate.opsForHash()
                    .put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo)));

        }
    }

    @Override
    public void clearCart() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        stringRedisTemplate.delete(cartKey);
    }

    @Override
    public List<CartInfo> getAllChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        List<Object> objectList = stringRedisTemplate.opsForHash().values(cartKey);       // 获取所有的购物项数据
        if(CollUtil.isNotEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList ;
        }
        return new ArrayList<>() ;
    }


    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }
}
