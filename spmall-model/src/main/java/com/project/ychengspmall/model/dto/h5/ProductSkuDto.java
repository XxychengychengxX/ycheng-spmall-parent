package com.project.ychengspmall.model.dto.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品列表搜索条件实体类
 */
@Data
public class ProductSkuDto {

    //关键字
    private String keyword;

    //品牌id
    private Long brandId;

    //一级分类id
    private Long category1Id;

    //二级分类id
    private Long category2Id;

    //三级分类id
    private Long category3Id;

    //排序（综合排序:1 价格升序:2 价格降序:3）
    private Integer order = 1;

}