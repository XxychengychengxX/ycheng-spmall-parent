package com.project.ychengspmall.manager.service;

import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.model.dto.product.CategoryBrandDto;
import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandService {
    List<Brand> findBrandByCategoryId(Long categoryId);

    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);
}
