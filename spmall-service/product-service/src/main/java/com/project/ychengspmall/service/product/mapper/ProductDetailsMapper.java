package com.project.ychengspmall.service.product.mapper;

import com.project.ychengspmall.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {

    ProductDetails getByProductId(Long productId);
}
