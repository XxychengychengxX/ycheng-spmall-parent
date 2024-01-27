package com.project.ychengspmall.service.product.mapper;

import com.project.ychengspmall.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product getById(Long productId);
}
