package com.project.ychengspmall.manager.mapper;

import com.project.ychengspmall.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> findByPage();

    Integer save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}
