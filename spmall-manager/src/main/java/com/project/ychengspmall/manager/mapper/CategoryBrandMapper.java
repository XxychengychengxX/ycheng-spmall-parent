package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.dto.product.CategoryBrandDto;
import com.project.ychengspmall.model.entity.product.Brand;
import com.project.ychengspmall.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
     List<Brand> findBrandByCategoryId(Long categoryId);

  List<CategoryBrand> findByPage(CategoryBrandDto CategoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);
}
