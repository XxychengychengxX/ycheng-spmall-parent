package com.project.ychengspmall.model.vo.h5;

import com.alibaba.fastjson.JSONArray;
import com.project.ychengspmall.model.entity.product.Product;
import com.project.ychengspmall.model.entity.product.ProductSku;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 商品详情对象
 */
@Data
public class ProductItemVo {

    //商品sku信息
    private ProductSku productSku;
    //商品信息
    private Product product;
    //商品轮播图列表
    private List<String> sliderUrlList;
    //商品详情图片列表
    private List<String> detailsImageUrlList;
    //商品规格信息
    private JSONArray specValueList;
    //商品规格对应商品skuId信息
    private Map<String, Object> skuSpecValueMap;

}