package com.project.ychengspmall.manager.service;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.entity.product.Brand;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    Integer save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();

}
