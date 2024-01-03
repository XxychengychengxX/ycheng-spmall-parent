package com.project.ychengspmall.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.ychengspmall.manager.mapper.CategoryBrandMapper;
import com.project.ychengspmall.manager.service.CategoryBrandService;
import com.project.ychengspmall.manager.service.CategoryService;
import com.project.ychengspmall.model.dto.product.CategoryBrandDto;
import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.model.entity.product.CategoryBrand;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Resource
    CategoryBrandMapper categoryBrandMapper;
    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {

        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page , limit) ;
        List<CategoryBrand> categoryBrandList = categoryBrandMapper.findByPage(categoryBrandDto) ;
        return new PageInfo<>(categoryBrandList);

    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand) ;
    }

    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }
}
