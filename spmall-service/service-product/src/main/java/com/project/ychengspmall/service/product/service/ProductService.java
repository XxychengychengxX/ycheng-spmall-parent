package com.project.ychengspmall.service.product.service;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.h5.ProductSkuDto;
import com.project.ychengspmall.model.entity.product.ProductSku;
import com.project.ychengspmall.model.vo.h5.ProductItemVo;

import java.util.List;

public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(Long skuId);

    ProductSku getBySkuId(Long skuId);
}
