package com.project.ychengspmall.service.product.mapper;

import com.project.ychengspmall.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> findAll();

}
