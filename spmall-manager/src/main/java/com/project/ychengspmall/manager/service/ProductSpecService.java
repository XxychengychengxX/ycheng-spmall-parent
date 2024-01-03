package com.project.ychengspmall.manager.service;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecService {
    List<ProductSpec> findAll();


    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);
}
