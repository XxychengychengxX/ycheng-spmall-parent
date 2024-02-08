package com.project.ychengspmall.service.product.mapper;

import com.project.ychengspmall.model.dto.h5.ProductSkuDto;
import com.project.ychengspmall.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 @author XxychengychengxX
 @date 2024/2/1
 */
@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    ProductSku getById(Long skuId);

    List<ProductSku> findByProductId(Long productId);
}
